// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal.config;

import com.h3c.uploads.internal.presenters.UploadMainFrameSubLocationPresenter;
import com.google.gwt.inject.client.AsyncProvider;
import com.h3c.uploads.internal.presenters.UploadsLocationPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.siemens.splm.clientfx.base.extensions.published.IModuleInjector;
import com.h3c.uploads.internal.config.H3cUploadsModule;

/**
 * access functions to injected types.  This is limited to the module level access as 
 * we are in the internal namespace.
 */
@GinModules( H3cUploadsModule.class )
public interface IH3cUploadsInjector
    extends IModuleInjector
{
    /** Injector instance */
    IH3cUploadsInjector INSTANCE = GWT.create( IH3cUploadsInjector.class );

    /**
     * Return Uploads Location presenter
     * @return Uploads location presenter
     */
    AsyncProvider<UploadsLocationPresenter> getUploadsLocationPresenter();

    /**
     * Return UploadMainFrame presenter
     * @return UploadMainFrame presenter
     */
    AsyncProvider<UploadMainFrameSubLocationPresenter> getUploadMainFrameSubLocationPresenter();

}
