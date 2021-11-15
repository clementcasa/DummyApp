package com.example.dummyapp.presentation.models.userTitle

import com.example.dummyapp.domain.models.DomainUserTitle

fun DomainUserTitle.toUIItem(): UIUserTitleItem =
    when(this) {
        DomainUserTitle.MR -> UIUserTitleItem.MR
        DomainUserTitle.MS -> UIUserTitleItem.MS
        DomainUserTitle.MRS -> UIUserTitleItem.MRS
        DomainUserTitle.MISS -> UIUserTitleItem.MISS
        DomainUserTitle.DR -> UIUserTitleItem.DR
        DomainUserTitle.NONE -> UIUserTitleItem.NONE
    }