package ar.com.auster.wifi.portal_server.meraki.model;

public class LincesingObject {

    private enum LicensingModel{
        CoTerm("co-term"),
        PerDevice("per-device"),
        Subscription("subscription");

        private String valor;
        private LicensingModel(String v) {
            this.valor = v;
        }

        private String getValor() {
            return valor;
        }

        public static LicensingModel fromValor(String v) {
            for (LicensingModel item : LicensingModel.values()) {
                if (item.valor.equals(v)) {
                    return item;
                }
            }
            return null;
        }

    }

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LicensingModel getModelEnum() {
        return LicensingModel.fromValor(this.model);
    }

    public void setModelEnum(LicensingModel v) {
        this.model = v != null? v.valor : null;
    }
}
