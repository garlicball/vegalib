package com.thesmartkbd.vagalib.logging.standard;

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

/* Creates on 2019/11/05. */

import com.thesmartkbd.vagalib.logging.Logger;
import com.thesmartkbd.vagalib.logging.LoggerAdapter;

/**
 * @author thesmartkbd
 */
public class StandardLoggerAdapter implements LoggerAdapter {

    @Override
    public Logger getLogger(String name) {
        return new StandardLogger(name);
    }

    @Override
    public Logger getLogger(Class<?> _class) {
        return new StandardLogger(_class);
    }

}

