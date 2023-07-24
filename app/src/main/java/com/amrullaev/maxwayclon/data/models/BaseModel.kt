package com.amrullaev.maxwayclon.data.models

import java.sql.Timestamp

abstract class BaseModel(
    var id: Long,
    var createdDate: Timestamp
)