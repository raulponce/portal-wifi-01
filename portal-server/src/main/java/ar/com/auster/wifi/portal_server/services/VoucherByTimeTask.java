package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.api.v1.Vouchers;
import ar.com.auster.wifi.portal_server.model.ClientVoucherStatus;
import ar.com.auster.wifi.portal_server.model.Voucher;
import ar.com.auster.wifi.portal_server.model.VoucherTimeUnit;
import ar.com.auster.wifi.portal_server.model.VoucherType;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherByTimeTask implements Runnable, IVoucherByTimeTask {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(VoucherByTimeTask.class);

    private ISessionService sessionService;

    @Getter
    private List<ClientVoucher> listClients = new ArrayList<>();

    private boolean finalize = false;
    private boolean running = false;
    private Thread taskThread = null;

    public static class ClientVoucher {
        public Voucher voucher;
        public Vouchers.OmadaQParam omadaQParam;
        public OffsetDateTime startTime;
        public OffsetDateTime endTime;
        public ClientVoucherStatus status;
    }

    public void setSessionService(ISessionService service) {
        this.sessionService = service;
    }

    public void add(Vouchers.OmadaQParam qparam, Voucher<VoucherTimeUnit> voucher) {
        if (voucher.getType() != VoucherType.BY_TIME)
            return;
        ClientVoucher item = new ClientVoucher();
        item.voucher = voucher;
        item.omadaQParam = qparam;
        item.startTime = OffsetDateTime.now();
        item.endTime = item.startTime;

        if (voucher.getData() != null) {
            if (VoucherTimeUnit.MINUTE.name().equals(voucher.getData().getUnit())) {
                item.endTime = item.startTime.plusMinutes(voucher.getData().getValue());
            } else if (VoucherTimeUnit.HOUR.name().equals(voucher.getData().getUnit())) {
                item.endTime = item.startTime.plusHours(voucher.getData().getValue());
            } else if (VoucherTimeUnit.DAY.name().equals(voucher.getData().getUnit()))  {
                item.endTime = item.startTime.plusDays(voucher.getData().getValue());
            }
        }
        synchronized (listClients) {
            listClients.add(item);
            log.info("Cliente {} ({}) agregado al control: {} - {}", item.omadaQParam.clientMac, item.omadaQParam.clientIp, item.startTime.toString(), item.endTime.toString());
        }
    }

    public synchronized void startTask() {
        if (!running) {
            taskThread = new Thread(this);
            taskThread.start();
        }
    }

    public synchronized void stopTask() {
        if (!running && !finalize) {
            finalize = true;
            notifyAll();
            try {
                taskThread.join();
            } catch (InterruptedException e) {}
        }
    }

    public synchronized boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        running = true;
        log.info("VoucherByTimeTask begin");
        do {
            List<ClientVoucher> tmpList = new ArrayList<>();
            List<ClientVoucher> toRemove = new ArrayList<>();
            synchronized (listClients) {
                tmpList.addAll(listClients);
            }
            OffsetDateTime timeAct = OffsetDateTime.now();
            tmpList.forEach(clientVoucher -> {
                if (clientVoucher.endTime.isBefore(timeAct)) {
                    toRemove.add(clientVoucher);
                    log.info("Cliente {} ({}) encolado para ser removido...", clientVoucher.omadaQParam.clientMac, clientVoucher.omadaQParam.clientIp);
                }
            });
            synchronized (listClients) {
                listClients.removeAll(toRemove);
            }

            toRemove.forEach(clientVoucher -> {
                new Thread(() -> {
                    boolean unAuthStatus = sessionService.unAuth(clientVoucher.omadaQParam.site, clientVoucher.omadaQParam.clientMac);
                    log.info("Cliente {} ({}) unAuth: {} - [{} - {}] [{}]", clientVoucher.omadaQParam.clientMac, clientVoucher.omadaQParam.clientIp, unAuthStatus, clientVoucher.startTime.toString(), clientVoucher.endTime.toString(), OffsetDateTime.now());
                }).start();
            });

            synchronized (this) {
                try {
                    wait(30000);
                } catch(InterruptedException e) {}
            }

        } while (!finalize);
        running = false;
        log.info("VoucherByTimeTask end");
    }
}
