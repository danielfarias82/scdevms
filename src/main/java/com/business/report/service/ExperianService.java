package com.business.report.service;

import com.business.report.model.ExperianRequest;
import com.business.report.model.ExperianResponse;

public interface ExperianService {
    ExperianResponse fetchDataFromExperian(ExperianRequest request);
}
