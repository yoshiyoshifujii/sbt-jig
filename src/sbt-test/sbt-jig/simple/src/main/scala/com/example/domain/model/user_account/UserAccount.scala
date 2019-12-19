package com.example.domain.model.user_account

import com.example.domain.model.contract.Contract

case class UserAccount(
    contract: Contract,
    userName: UserName
)
