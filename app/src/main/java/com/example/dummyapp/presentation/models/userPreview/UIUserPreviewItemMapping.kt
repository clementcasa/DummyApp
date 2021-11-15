package com.example.dummyapp.presentation.models.userPreview

import com.example.dummyapp.domain.models.DomainUserPreview
import com.example.dummyapp.presentation.models.userTitle.toUIItem

fun DomainUserPreview.toUIItem(): UIUserPreviewItem =
    UIUserPreviewItem(id, title.toUIItem(), firstName, lastName, picture)