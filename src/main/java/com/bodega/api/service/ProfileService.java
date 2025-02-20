package com.bodega.api.service;

import com.bodega.api.io.ProfileEntity;
import com.bodega.api.shared.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
  List<ProfileDto> getGeneralProfiles();
}
