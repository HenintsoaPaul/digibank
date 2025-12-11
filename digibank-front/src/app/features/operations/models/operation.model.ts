export interface Operation {
  id: number;
  dateStr: Date;
  amount: number;
  type: "debit" | "credit";
  status: "pending" | "validated";
  description: string;
}
