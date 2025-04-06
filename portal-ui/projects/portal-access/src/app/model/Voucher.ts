export enum VoucherType {
    BY_TIME='BY_TIME', UNKNOWN='UNKNOWN'
}

export enum VoucherPriceCurrency {
    USD='USD', ARS='ARS'
}

export class VoucherPrice {
    public currency!: VoucherPriceCurrency
    public cost!: number
}

export enum VoucherTimeUnit {
    MINUTE='MINUTE', HOUR='HOUR', DAY='DAY'
}

export class VoucherByTimeData {
    public timeUnit! : VoucherTimeUnit
    public value!: number
}

export class Voucher {
    public type!: VoucherType
    public vuid!: string
    public price!: VoucherPrice
    public data! : VoucherByTimeData
}