import React, { useState, useEffect } from "react";
import Link from "next/link";
import axios from "axios";

const initialState = {
  vouchers: [],
  error: null,
};

const VoucherService = () => {
  const [vouchers, setVouchers] = useState(initialState.vouchers);
  const [error, setError] = useState(initialState.error);

  useEffect(() => {
    axios
      .get("/api/vouchers")
      .then((response) => {
        setVouchers(response.data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, []);

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div>
      <h1>Vouchers</h1>
    </div>
  );
};

export default VoucherService;
