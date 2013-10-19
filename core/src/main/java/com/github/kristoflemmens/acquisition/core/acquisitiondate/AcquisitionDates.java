package com.github.kristoflemmens.acquisition.core.acquisitiondate;

import java.util.Date;

public interface AcquisitionDates {
    Date dateFor(String organismCode, String companyId);
}
