package com.amrullaev.maxwayclon.utils.extensions

import com.amrullaev.maxwayclon.data.models.Address
import com.google.android.gms.maps.model.LatLng


fun LatLng.toAddress(): Address = Address(this.latitude,this.longitude)