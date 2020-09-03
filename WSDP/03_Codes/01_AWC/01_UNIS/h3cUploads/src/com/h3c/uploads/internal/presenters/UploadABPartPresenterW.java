// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal.presenters;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;
import com.h3c.uploads.internal.uihandlers.BatchChangeUiHandlers;
import com.h3c.uploads.internal.uihandlers.UploadABPartUiHandlers;
import com.h3c.uploads.resources.i18n.H3cUploadsMessages;
import com.siemens.splm.clientfx.base.published.IMessageService;
import com.siemens.splm.clientfx.tcui.create.internal.presenters.CreateObjectPresenterW;
import com.siemens.splm.clientfx.ui.published.presenters.AbstractToolsAndInfoAreaPresenterWidget;

/**
 * Presenter widget for NotesToPDM.
 */
public class UploadABPartPresenterW
    extends AbstractToolsAndInfoAreaPresenterWidget<UploadABPartPresenterW.MyView>
    implements UploadABPartUiHandlers
{
   
    /** message service */
    @Inject
    private IMessageService m_messageService;
    
    /**
     * CreateObject presenter widget
     */
    private CreateObjectPresenterW m_createObjectPresenterW;

    /**
     * FMS ticket
     */
    private String m_fmsTicket;

    /**
     * Dataset commit callback
     */
    private AsyncCallback<String> m_datasetCommitCallback;

    /**
     * The event bus to fire events on
     */
    @Inject
    private EventBus m_eventBus;

    


    /**
     * View interface
     */
    public interface MyView
        extends View, HasUiHandlers<UploadABPartUiHandlers>
    {
        
    }

    /**
     * Constructor
     * 
     * @param eventBus The Event bus.
     * @param view The View for this presenter.
     */
    @Inject
    public UploadABPartPresenterW( EventBus eventBus, MyView view )
    {
        super( eventBus, view );
        view.setUiHandlers( this );
    }

    @Override
    public String getTitle()
    {
        return H3cUploadsMessages.INSTANCE.UploadABPartTitle();
    }
    
  
    @Override
    public void completed()
    {
        //Notify the user that the operation is complete.
        m_messageService.notify( H3cUploadsMessages.INSTANCE.NotesToPDMComplete() );
        for(int i=1;i<10;i++)
        {
        	m_messageService.notify("========xxxxxxxx-------:"+i );
        }

        //Close the panel.
        getSubLocation().setActiveToolsAndInfoCommand( null );
    }
    

    /**
     * Set CreateObjectPresenterW
     *
     * @param createObjectPresenterW createObjectPresenterW
     */
    public void setCreateObjectPresenterW( CreateObjectPresenterW createObjectPresenterW )
    {
        m_createObjectPresenterW = createObjectPresenterW;
    }




	@Override
	public void uploadFile() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showMessage(String message)
	{
		
		
	}

 

}
