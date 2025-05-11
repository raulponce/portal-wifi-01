package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.model.Voucher;

import java.util.List;

public interface IDAOData {

    List<Voucher> getVouchers();
}
