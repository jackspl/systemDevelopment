// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal.commands;

import com.siemens.splm.clientfx.ui.published.commands.AbstractToolsAndInfoCommandHandler;
import com.siemens.splm.clientfx.ui.published.IToolsAndInfoAreaContent;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.siemens.splm.clientfx.base.published.AsyncCallbackWithContextPair;
import com.siemens.splm.clientfx.base.published.MessageUtil;
import com.siemens.splm.clientfx.base.published.config.IFxBaseGinjector;
import com.siemens.splm.clientfx.kernel.clientmodel.published.IJSO;
import com.siemens.splm.clientfx.kernel.clientmodel.published.JSOFactory;
import com.siemens.splm.clientfx.kernel.soa.published.ISOAResponse;
import com.siemens.splm.clientfx.kernel.soa.published.SOA;
import com.siemens.splm.clientfx.ui.commands.published.ICommandDisplay;
import com.h3c.uploads.internal.NameTokens;
import com.h3c.uploads.internal.presenters.UploadABPartPresenterW;

/**
 * Command handler for CMD_UPLoadABPART 
 */
public class UploadABPartCommandHandler
    extends AbstractToolsAndInfoCommandHandler
{    
    /**
     * Provider for the NotesToPDM presenter instance
     */
    @Inject
    private AsyncProvider<UploadABPartPresenterW> m_BatchChangePresenterProvider;
    
    /** The presenter for the UI */
    protected UploadABPartPresenterW m_presenter;
    
    /**
     * Constructor
     * 
     * @param commandDisplay Display to use for this handler
     */
    @Inject
    public UploadABPartCommandHandler( @Named( NameTokens.CMD_UPLoadABPART ) ICommandDisplay commandDisplay )
    {
        super( NameTokens.CMD_UPLoadABPART, commandDisplay );
    }
    
    @Override
    public void commandContextChanged()
    {
        //Call super implementation.
        super.commandContextChanged();

        // Update the model object shown in the object info presenter
        if( isActive() && m_presenter != null )
        {
           // m_presenter.setModelObject( getSelectedModelObject() );
        }

        //Code which toggles enabling/disabling of the command
        //may be added here.
        //
        //For this particular example, we will always leave the 
        //command enabled and visible.
        setIsVisible( true );
    }

    @Override
    public void getToolsAndInfoAreaContent( AsyncCallback<IToolsAndInfoAreaContent> callback )
    {
        //Implement the invocation/presentation of the panel.
        m_BatchChangePresenterProvider.get( new AsyncCallbackWithContextPair<UploadABPartCommandHandler, AsyncCallback<IToolsAndInfoAreaContent>, UploadABPartPresenterW>( this, callback )
        {
            @Override
            public void onFailure( UploadABPartCommandHandler handler, AsyncCallback<IToolsAndInfoAreaContent> callback2, Throwable caught )
            {
                callback2.onFailure( caught );
            }

            @Override
            public void onSuccess( final UploadABPartCommandHandler handler, final AsyncCallback<IToolsAndInfoAreaContent> callback2, final UploadABPartPresenterW presenter )
            {
//            	IJSO request = JSOFactory.createObject();
//                request.setString("commandID", NameTokens.CMD_UPLoadABPART);


//                SOA.executeOperation("Util-2015-10-AccessSevice", "getShowOrNot", request, new AsyncCallback<ISOAResponse>()
//                {
//
//                      @Override
//                      public void onFailure(Throwable caught)
//                      {
//                             MessageUtil.reportException(caught);
//                      }
//
//                      @Override
//                      public void onSuccess(ISOAResponse result)
//                      {
//                             if (result.getBoolean("out"))
//                             {
                            	//Store the handler so we can set new selections without closing the Panel.
                                 handler.m_presenter = presenter;
                                 //Set values on the presenter, as required.
                               //  presenter.setModelObject( getSelectedModelObject() );
                                 callback2.onSuccess( presenter );
//                             } 
//                             else
//                             {
//                                    IFxBaseGinjector.INSTANCE.getMessageService().notify("您没有权限执行该操作。");
//                             }
//                      }
//                });                
            }
        } );
    }

}
