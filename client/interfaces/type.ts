export interface ICampaign {
  id: number;
  // Add other relevant fields as per the CampaignEntity model
}

export interface IVoucher {
  id: number;
  campaignId: {
    id: number;
  };
  code: string;
  status: string;
  expirationDate: string;
  usageLimits: number;
  restrictions: string[];
  createdAt: string;
  updatedAt: string;
  voucherType: string;
  redeemDate: null | string;
  redeemedBy: null | string;
}
