package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.mapper.Tests;
import ar.com.auster.wifi.portal_server.model.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalService implements IPortalService {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(PortalService.class);

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IDAOData daoData;


//    @Autowired
//    private Tests tests;

//    @PostConstruct
//    private void onInit() {
//        if (tests != null) {
//            new Thread(() -> {
//                synchronized (this) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                if (tests != null) {
//                    tests.testVoucherMapper();
//                }
//            }).start();
//        }
//    }

    @Override
    public SessionInfo newDeviceAccess(PortalInfo portalinfo, Device device) {
        SessionInfo result = null;

        // 1.- validar portalinfo
        if (!sessionService.isValid(portalinfo)) {
            return null;
        }

        // TODO: Auditar

        // 2.- buscar device en db
        Device deviceDB = daoData.getDeviceByMac(device.getMac());
        if (deviceDB == null) {
            deviceDB = device;
            deviceDB.setClient(new Client());
            deviceDB.getClient().setId(null);
            deviceDB.setStatus(DeviceStatus.PENDING);
            deviceDB.setLastOperation(null);
            if (!daoData.addNewDevice(deviceDB)) {
                device = null;
            } else {
                // TODO: Auditar
                device = daoData.getDeviceByMac(device.getMac());
            }
        } else {
            device = deviceDB;
        }

        if (device != null) {
            //TODO:cargar 10 recen voucher from DB
            List<ClientVoucher> listRecentVouchers = daoData.getClienVouchers(device.getClient(), device.getMac());
            //TODO: asignar active voucher
            ClientVoucher activeVoucher = null;

            //?deberiamos persistir datos de aomada info?

            result = new SessionInfo();
            result.setDevice(device);
            result.setActiveVoucher(activeVoucher);
            result.setListRecentVouchers(listRecentVouchers);
        }

        return result;
    }

    @Override
    public List<Voucher> getVoucherAvailables(PortalInfo portalinfo) {
        List<Voucher> vouchers = new ArrayList<>();
        log.debug("Voucher list solicitadas");
        try {
            vouchers.addAll(daoData.getVouchersAvailables());
        } catch (Throwable e) {
            log.error("ERROR", e);
        }
        return vouchers;
    }

    @Override
    public BuyVoucherStatus buyVoucher(PortalInfo portalinfo, Client client, Device device, Voucher voucher) {
        // 1.- validar portalinfo
        if (!sessionService.isValid(portalinfo)) {
            return null;
        }

        // 2.- Validar Device
        // 2.1- TODO: Es un disopositivo idenificado en OmadaController
        // 2.2.- Cargar de Base de Datos
        Device deviceDB = daoData.getDeviceByMac(device.getMac());

        // 3.- Validar Voucher
        Voucher voucherDB = daoData.getVoucherById(voucher.getId());

        // 4.- Validar y asentar Asentar cliente
        Client clientDB = daoData.getClienById(client.getId());

        // 5.- Instanciar voucher

        return BuyVoucherStatus.IN_PROGRESS;
    }

    @Override
    public BuyVoucherStatus checkVoucherBuyed(PortalInfo portalinfo, Client client, Device device, Voucher voucher) {
        // 1.- validar portalinfo
        if (!sessionService.isValid(portalinfo)) {
            return null;
        }

        // 2.- Validar Device
        Device deviceDB = daoData.getDeviceByMac(device.getMac());

        // 3.- Validar compra voucher
        Voucher voucherDB = daoData.getVoucherById(voucher.getId());

        // 4.- Validar clientes
        Client clientDB = daoData.getClienById(client.getId());

        // 4.- Encolar voucher (instanciar voucher client)
        // Y si esta repetido?
        
        return BuyVoucherStatus.ACCEPTED;
    }
}
