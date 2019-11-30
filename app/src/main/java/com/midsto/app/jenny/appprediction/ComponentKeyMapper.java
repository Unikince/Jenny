/**
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.midsto.app.jenny.appprediction;

import android.content.Context;

import com.midsto.app.jenny.AppInfo;
import com.midsto.app.jenny.ItemInfoWithIcon;
import com.midsto.app.jenny.allapps.AllAppsStore;
import com.midsto.app.jenny.shortcuts.ShortcutKey;
import com.midsto.app.jenny.util.ComponentKey;

import static com.android.quickstep.InstantAppResolverImpl.COMPONENT_CLASS_MARKER;

public class ComponentKeyMapper {

    protected final ComponentKey componentKey;
    private final Context mContext;
    private final DynamicItemCache mCache;

    public ComponentKeyMapper(Context context, ComponentKey key, DynamicItemCache cache) {
        mContext = context;
        componentKey = key;
        mCache = cache;
    }

    public String getPackage() {
        return componentKey.componentName.getPackageName();
    }

    public String getComponentClass() {
        return componentKey.componentName.getClassName();
    }

    public ComponentKey getComponentKey() {
        return componentKey;
    }

    @Override
    public String toString() {
        return componentKey.toString();
    }

    public ItemInfoWithIcon getApp(AllAppsStore store) {
        AppInfo item = store.getApp(componentKey);
        if (item != null) {
            return item;
        } else if (getComponentClass().equals(COMPONENT_CLASS_MARKER)) {
            return mCache.getInstantApp(componentKey.componentName.getPackageName());
        } else if (componentKey instanceof ShortcutKey) {
            return mCache.getShortcutInfo((ShortcutKey) componentKey);
        }
        return null;
    }
}
