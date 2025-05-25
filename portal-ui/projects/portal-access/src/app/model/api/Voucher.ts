export enum VoucherType {
    BY_TIME='BY_TIME', BY_DATA='BY_DATA', UNKNOWN='UNKNOWN'
}

export enum VoucherStatus {
    READY='READY', UNAVAILABLE='UNAVAILABLE', DELETED='DELETED', ON_CONFIG='ON_CONFIG'
}

export enum VoucherPriceCurrency {
    FREE='FREE', USD='USD', ARS='ARS'
}

export class VoucherPrice {
    public currency!: VoucherPriceCurrency
    public cost!: number
}

export enum VoucherTimeUnit {
    MINUTE='MINUTE', HOUR='HOUR', DAY='DAY'
}

export enum VoucherDataUnit {
    MEGAS='MEGAS', GIGAS='GIGAS'
}

export class VoucherData {
    public unit! : VoucherTimeUnit|VoucherDataUnit
    public value!: number
}

export class Voucher {
    public id!: number
    public type!: VoucherType
    public name!: string
    public status!: VoucherStatus
    //public vuid!: string
    public price!: VoucherPrice
    public data! : VoucherData
}