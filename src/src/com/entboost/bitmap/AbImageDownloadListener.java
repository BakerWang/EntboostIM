/*
 * Copyright (C) 2015 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.entboost.bitmap;

import android.graphics.Bitmap;

// TODO: Auto-generated Javadoc
/**
 * 描述：图片下载的回调接口.
 *
 * @author amsoft.cn
 * @date 2011-12-10
 * @version v1.0
 */
public interface AbImageDownloadListener {
	
	/**
	 * 描述：更新视图.
	 *
	 * @param bitmap 下载返回的Bitmap
	 * @param imageUrl 原互联网下载地址
	 */
    public void update(Bitmap bitmap, String imageUrl); 

}
