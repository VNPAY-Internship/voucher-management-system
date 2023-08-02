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
  console.log(response.body);

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
    `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers`,
    fetcher
  );

  const {
    data: searchResults,
    error: searchError,
    isValidating: isSearchValidating,
  } = useSWR(
    searchTerm
      ? `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers/search?term=${searchTerm}`
      : null,
    fetcher
  );

  const vouchers = searchTerm ? searchResults : allVouchers;
  const error = searchTerm ? searchError : allVouchersError;
  const [newVoucher, setNewVoucher] = useState<Partial<IVoucher>>({}); // adjust this based on your actual voucher data structure

  const handleCreateVoucher = async (e: React.FormEvent) => {
    e.preventDefault();
    await createVoucher(
      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers`,
      newVoucher as IVoucher
    );
    mutate(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers`);
    setNewVoucher({}); // reset the form
  };

  useEffect(() => {
    console.log(`searchTerm: ${searchTerm}`);
    if (searchTerm) {
      mutate(async () => {
        const results = await searchVouchers(
          `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers/search`,
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
      <form>
        <label
          htmlFor="default-search"
          className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"
        >
          Search
        </label>
        <div className="relative">
          <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg
              className="w-4 h-4 text-gray-500 dark:text-gray-400"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 20 20"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
              />
            </svg>
          </div>
          <input
            type="search"
            id="default-search"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Search Vouchers ..."
            required
          />
          <button
            type="submit"
            className="text-white absolute right-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            Search
          </button>
        </div>
      </form>

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
        <input
          type="text"
          value={newVoucher.voucherType || ""}
          onChange={(e) =>
            setNewVoucher({ ...newVoucher, voucherType: e.target.value })
          }
          placeholder="Voucher type..."
        />
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
            <th className="py-4 px-6 sticky top-0 border-b border-gray-200 bg-slate-300 text-gray-600">
              DELETE
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
                  className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                  onClick={async () => {
                    await deleteVoucher(
                      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers/${voucher.id}`
                    );
                    mutate(
                      `${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/vouchers`
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
