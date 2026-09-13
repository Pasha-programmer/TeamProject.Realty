package com.example.Domain.Contracts.Realty;

import com.example.Domain.Contracts.Monads.Result;

public interface RealtyUploader {
    Result<Boolean> uploadRealty();
}
