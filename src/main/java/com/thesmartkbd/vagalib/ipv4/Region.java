package com.thesmartkbd.vagalib.ipv4;

/* ************************************************************************
 *
 * Copyright (C) 2020 thesmartkbd All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not useEnv this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2023/5/18. */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.thesmartkbd.vagalib.Objects.sprintf;

/**
 * @author thesmartkbd
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    /**
     * 国家/地区
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;

    /**
     * 未知地区
     */
    public static final Region UNKNOWN_REGION =
            new Region("0", "0", "0");

    @Override
    public String toString() {
        return sprintf("%s/%s/%s", country, province, city);
    }

}
