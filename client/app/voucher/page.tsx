"use client";

import { useEffect, useState } from "react";
import useSWR, { mutate } from "swr";
import { IVoucher } from "@/interfaces/type";

type FetcherFunc<T> = (...args: any[]) => Promise<T>;
const fetcher: FetcherFunc<IVoucher[]> = (url: string) =>
  fetch(url).then((res) => res.json());

async function createVoucher(url: string, voucher: IVoucher) {
  const response = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(voucher),
  });

  if (!response.ok) throw new Error(response.statusText);

  return await response.json();
}

async function updateVoucher(url: string, voucher: IVoucher) {
  const response = await fetch(url, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(voucher),
  });

  if (!response.ok) throw new Error(response.statusText);

  return await response.json();
}

async function deleteVoucher(url: string) {
  const response = await fetch(url, {
    method: "DELETE",
  });

  if (!response.ok) throw new Error(response.statusText);

  return await response.json();
}

async function searchVouchers(url: string, searchTerm: string) {
  const response = await fetch(`${url}?term=${searchTerm}`);

  if (!response.ok) throw new Error(response.statusText);

  return await response.json();
}

export default function VoucherPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const { data: allVouchers, error: allVouchersError } = useSWR(
    `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers`,
    fetcher
  );

  const {
    data: searchResults,
    error: searchError,
    isValidating: isSearchValidating,
  } = useSWR(
    searchTerm
      ? `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers/search?term=${searchTerm}`
      : null,
    fetcher
  );

  const vouchers = searchTerm ? searchResults : allVouchers;
  const error = searchTerm ? searchError : allVouchersError;
  const [newVoucher, setNewVoucher] = useState<Partial<IVoucher>>({}); // adjust this based on your actual voucher data structure

  const handleCreateVoucher = async (e: React.FormEvent) => {
    e.preventDefault();
    await createVoucher(
      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers`,
      newVoucher as IVoucher
    );
    mutate(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers`);
    setNewVoucher({}); // reset the form
  };

  useEffect(() => {
    console.log(`searchTerm: ${searchTerm}`);
    if (searchTerm) {
      mutate(async () => {
        const results = await searchVouchers(
          `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers/search`,
          searchTerm
        );
        console.log(results);
        return results;
      });
    }
  }, [searchTerm]);

  if (error) return <div>Failed to load</div>;
  if (!vouchers) return <div>Loading...</div>;

  return (
    <div className="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
      <input
        type="text"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="Search vouchers..."
      />

      <form onSubmit={handleCreateVoucher}>
        <input
          type="text"
          value={newVoucher.code || ""}
          onChange={(e) =>
            setNewVoucher({ ...newVoucher, code: e.target.value })
          }
          placeholder="Code..."
        />
        <input
          type="number"
          value={newVoucher.usageLimits || ""}
          onChange={(e) =>
            setNewVoucher({
              ...newVoucher,
              usageLimits: parseInt(e.target.value),
            })
          }
          placeholder="Usage limits..."
        />
        <input
          type="text"
          value={newVoucher.voucherType || ""}
          onChange={(e) =>
            setNewVoucher({ ...newVoucher, voucherType: e.target.value })
          }
          placeholder="Voucher type..."
        />

        {/* Add fields for other voucher properties here */}

        <button type="submit">Create Voucher</button>
      </form>
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
              <td>
                <button
                  onClick={async () => {
                    await deleteVoucher(
                      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers/${voucher.id}`
                    );
                    mutate(
                      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/vouchers`
                    );
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
