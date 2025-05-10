package ar.com.auster.wifi.portal_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class VoucherData<T>  {

    @Getter
    @Setter
    private T unit; //DATA_ENUM
    @Getter @Setter
    private int value; //DATA_UINT
}

