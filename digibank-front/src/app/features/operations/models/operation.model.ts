export interface Operation {
  id: number;
  dateStr: Date;
  amount: number;
  type: OperationType;
  status: OperationStatus;
  description: string;
}

export enum OperationType {
  Debit = "Debit",
  Credit = "Credit"
}

export enum OperationStatus {
  Pending = "Pending",
  Validated = "Validated"
}

export class OperationReq {
  amount: number;
  type: OperationType;
  description: string;

  constructor(amount: number, type: OperationType, description: string) {
    this.amount = amount;
    this.type = type;
    this.description = description;
  }
}
