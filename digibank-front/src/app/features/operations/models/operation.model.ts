export interface Operation {
  id: number;
  date?: Date;
  amount: number;
  type: "debit" | "credit";
  description: string;
}
