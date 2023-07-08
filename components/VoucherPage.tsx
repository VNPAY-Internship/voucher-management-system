// pages/VoucherPage.tsx
"use client";

import { useEffect, useState } from "react";
import axios from "axios";
import { IVoucher } from "@/interfaces/type";

export default function VoucherPage() {
  const [vouchers, setVouchers] = useState<IVoucher[]>([]);

  useEffect(() => {
    const fetchVouchers = async () => {
      try {
        const res = await axios.get(
          `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers`
        );
        setVouchers(res.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchVouchers();
  }, []);

  return (
    <div className="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
      <table className="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
        <thead>
          <tr className="text-left">
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              ID
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Campaign ID
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Code
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Status
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Expiration Date
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Usage Limits
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Restrictions
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Created At
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Updated At
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Voucher Type
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Redeem Date
            </th>
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              Redeemed By
            </th>
          </tr>
        </thead>
        <tbody>
          {vouchers.map((voucher: IVoucher) => (
            <tr key={voucher.id} className="hover:bg-slate-300 text-center">
              <td className="py-4 px-6">{voucher.id}</td>
              <td className="py-4 px-6">{voucher.campaignId.id}</td>
              <td className="py-4 px-6">{voucher.code}</td>
              <td className="py-4 px-6">{voucher.status}</td>
              <td className="py-4 px-6">
                {new Date(voucher.expirationDate).toLocaleDateString()}
              </td>
              <td className="py-4 px-6">{voucher.usageLimits}</td>
              <td className="py-4 px-6">{voucher.restrictions.join(", ")}</td>
              <td className="py-4 px-6">
                {new Date(voucher.createdAt).toLocaleDateString()}
              </td>
              <td className="py-4 px-6">
                {new Date(voucher.updatedAt).toLocaleDateString()}
              </td>
              <td className="py-4 px-6">{voucher.voucherType}</td>
              <td className="py-4 px-6">
                {voucher.redeemDate
                  ? new Date(voucher.redeemDate).toLocaleDateString()
                  : ""}
              </td>
              <td className="py-4 px-6">{voucher.redeemedBy}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
