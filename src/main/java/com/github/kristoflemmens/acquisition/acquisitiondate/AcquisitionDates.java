package com.github.kristoflemmens.acquisition.acquisitiondate;

import java.util.Date;

public interface AcquisitionDates {
    Date dateFor(String organismCode, String companyId);
}
