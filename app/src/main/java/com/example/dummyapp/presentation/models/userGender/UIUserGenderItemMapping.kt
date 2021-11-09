package com.example.dummyapp.presentation.models.userGender

import com.example.dummyapp.domain.models.DomainUserGender

fun DomainUserGender.toUIItem(): UIUserGenderItem =
    when(this) {
        DomainUserGender.MALE -> UIUserGenderItem.MALE
        DomainUserGender.FEMALE -> UIUserGenderItem.FEMALE
        DomainUserGender.OTHER -> UIUserGenderItem.OTHER
        DomainUserGender.NONE -> UIUserGenderItem.NONE
    }