// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal.config;

import com.siemens.splm.clientfx.ui.theme.published.ThemeExtensionPointHelper;
import com.siemens.splm.clientfx.ui.theme.published.ITheme;
import com.h3c.uploads.internal.views.BOMUploadView;
import com.h3c.uploads.internal.views.BatchChangeMfgpartView;
import com.h3c.uploads.internal.views.BatchChangeView;
import com.h3c.uploads.internal.views.BatchDownloadBOMView;
import com.h3c.uploads.internal.views.DataReleaseView;
import com.h3c.uploads.internal.views.LstUploadView;
import com.h3c.uploads.internal.views.MashDownloadView;
import com.h3c.uploads.internal.views.NotesToPDMView;
import com.h3c.uploads.internal.views.PartRoleUploadView;
import com.h3c.uploads.internal.views.RoleCfgUploadView;
import com.h3c.uploads.internal.views.SingleApplyDataView;
import com.h3c.uploads.internal.views.TlbUploadView;
import com.h3c.uploads.internal.views.UploadABPartView;
import com.h3c.uploads.internal.presenters.BOMUploadPresenterW;
import com.h3c.uploads.internal.commands.BOMUploadCommandHandler;
import com.h3c.uploads.internal.commands.BOMUploadCommandDisplay;
import com.h3c.uploads.internal.commands.BatchChangeCommandDisplay;
import com.h3c.uploads.internal.commands.BatchChangeCommandHandler;
import com.h3c.uploads.internal.commands.BatchChangeMfgpartCommandDisplay;
import com.h3c.uploads.internal.commands.BatchChangeMfgpartCommandHandler;
import com.h3c.uploads.internal.commands.BatchDownloadBOMCommandDisplay;
import com.h3c.uploads.internal.commands.BatchDownloadBOMCommandHandler;
import com.h3c.uploads.internal.commands.DataReleaseCommandDisplay;
import com.h3c.uploads.internal.commands.DataReleaseCommandHandler;
import com.h3c.uploads.internal.commands.LstUploadCommandDisplay;
import com.h3c.uploads.internal.commands.LstUploadCommandHandler;
import com.h3c.uploads.internal.commands.MashDownloadCommandDisplay;
import com.h3c.uploads.internal.commands.MashDownloadCommandHandler;
import com.h3c.uploads.internal.commands.NotesToPDMCommandDisplay;
import com.h3c.uploads.internal.commands.NotesToPDMCommandHandler;
import com.h3c.uploads.internal.commands.PartRoleUploadCommandDisplay;
import com.h3c.uploads.internal.commands.PartRoleUploadCommandHandler;
import com.h3c.uploads.internal.commands.RoleCfgUploadCommandDisplay;
import com.h3c.uploads.internal.commands.RoleCfgUploadCommandHandler;
import com.h3c.uploads.internal.commands.SingleApplyDataCommandDisplay;
import com.h3c.uploads.internal.commands.SingleApplyDataCommandHandler;
import com.h3c.uploads.internal.commands.TlbUploadCommandDisplay;
import com.h3c.uploads.internal.commands.TlbUploadCommandHandler;
import com.h3c.uploads.internal.commands.UploadABPartCommandDisplay;
import com.h3c.uploads.internal.commands.UploadABPartCommandHandler;
import com.h3c.uploads.internal.commands.UploadCommandGroupDisplay;
import com.h3c.uploads.internal.commands.UploadCommandGroupHandler;
import com.siemens.splm.client.favorites.internal.config.FavoritesGinModule;
import com.siemens.splm.clientfx.tcui.xrt.published.commands.TcUIXRTCommands;
import com.siemens.splm.clientfx.ui.commands.published.CommandInCommandGroupKey;
import com.siemens.splm.clientfx.ui.commands.published.CommandsExtensionPointHelper;
import com.siemens.splm.clientfx.ui.commands.published.CommandId;
import com.siemens.splm.clientfx.ui.commands.published.ICommandDisplay;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.inject.Inject;
import com.h3c.uploads.internal.presenters.UploadMainFrameSubLocationPresenter;
import com.h3c.uploads.internal.views.UploadMainFramePrimaryWorkAreaView;
import com.h3c.uploads.internal.presenters.BatchChangeMfgpartPresenterW;
import com.h3c.uploads.internal.presenters.BatchChangePresenterW;
import com.h3c.uploads.internal.presenters.BatchDownloadBOMPresenterW;
import com.h3c.uploads.internal.presenters.DataReleasePresenterW;
import com.h3c.uploads.internal.presenters.LstUploadPresenterW;
import com.h3c.uploads.internal.presenters.MashDownloadPresenterW;
import com.h3c.uploads.internal.presenters.NotesToPDMPresenterW;
import com.h3c.uploads.internal.presenters.PartRoleUploadPresenterW;
import com.h3c.uploads.internal.presenters.RoleCfgUploadPresenterW;
import com.h3c.uploads.internal.presenters.SingleApplyDataPresenterW;
import com.h3c.uploads.internal.presenters.TlbUploadPresenterW;
import com.h3c.uploads.internal.presenters.UploadABPartPresenterW;
import com.h3c.uploads.internal.presenters.UploadMainFramePrimaryWorkAreaPresenter;
import com.h3c.uploads.internal.presenters.UploadsLocationPresenter;
import com.siemens.splm.clientfx.ui.published.config.DefaultSubLocationContribution;
import com.siemens.splm.clientfx.ui.published.config.SubLocationExtensionPointHelper;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.google.inject.Provider;
import com.h3c.uploads.internal.NameTokens;
import com.h3c.uploads.resources.i18n.H3cUploadsMessages;

/**
 * Module H3cUploads.
 */
public class H3cUploadsModule
    extends AbstractPresenterModule
{
    @Override
    protected void configure()
    {

        bindPresenter( UploadsLocationPresenter.class, UploadsLocationPresenter.MyProxy.class );

//        SubLocationExtensionPointHelper.declareSubLocationExtensionPoint( binder(), NameTokens.Uploads_LOCATION );

        bindPresenter( UploadMainFrameSubLocationPresenter.class, UploadMainFrameSubLocationPresenter.MyProxy.class );

        bindSingletonPresenterWidget( UploadMainFramePrimaryWorkAreaPresenter.class,UploadMainFramePrimaryWorkAreaPresenter.MyView.class, UploadMainFramePrimaryWorkAreaView.class );

        SubLocationExtensionPointHelper.declareSubLocationExtensionPoint( binder(), NameTokens.Uploads_LOCATION );
        
        // Configure sublocations
        SubLocationExtensionPointHelper.contributeSubLocation( binder(), NameTokens.Uploads_LOCATION,
        		UploadMainFrameSubLocationContribution.class );
        // Add configuration here.
        bindSingletonPresenterWidget( BOMUploadPresenterW.class, BOMUploadPresenterW.MyView.class, BOMUploadView.class );
        bindSingletonPresenterWidget( LstUploadPresenterW.class, LstUploadPresenterW.MyView.class, LstUploadView.class );
        bindSingletonPresenterWidget( NotesToPDMPresenterW.class, NotesToPDMPresenterW.MyView.class, NotesToPDMView.class );
        bindSingletonPresenterWidget( MashDownloadPresenterW.class, MashDownloadPresenterW.MyView.class, MashDownloadView.class );
        bindSingletonPresenterWidget( BatchChangePresenterW.class, BatchChangePresenterW.MyView.class, BatchChangeView.class );
        bindSingletonPresenterWidget( BatchChangeMfgpartPresenterW.class, BatchChangeMfgpartPresenterW.MyView.class, BatchChangeMfgpartView.class );
        bindSingletonPresenterWidget( DataReleasePresenterW.class, DataReleasePresenterW.MyView.class, DataReleaseView.class );
        bindSingletonPresenterWidget( SingleApplyDataPresenterW.class, SingleApplyDataPresenterW.MyView.class, SingleApplyDataView.class );
        bindSingletonPresenterWidget( TlbUploadPresenterW.class, TlbUploadPresenterW.MyView.class, TlbUploadView.class );
        bindSingletonPresenterWidget( PartRoleUploadPresenterW.class, PartRoleUploadPresenterW.MyView.class, PartRoleUploadView.class );
        bindSingletonPresenterWidget( RoleCfgUploadPresenterW.class, RoleCfgUploadPresenterW.MyView.class, RoleCfgUploadView.class );
        bindSingletonPresenterWidget( BatchDownloadBOMPresenterW.class, BatchDownloadBOMPresenterW.MyView.class, BatchDownloadBOMView.class );
        

        bindSingletonPresenterWidget( UploadABPartPresenterW.class, UploadABPartPresenterW.MyView.class, UploadABPartView.class );
      
        
//        bindUploadsCommandWidgets(NameTokens.UploadMainFrame_SUB_LOCATION);
        
        // Links the new command's display to its handler
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_BOMUpload, NameTokens.CMD_BOMUpload, BOMUploadCommandHandler.class, BOMUploadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_LstUpload, NameTokens.CMD_LstUpload, LstUploadCommandHandler.class, LstUploadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_NotesToPDM, NameTokens.CMD_NotesToPDM, NotesToPDMCommandHandler.class, NotesToPDMCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_MashDownload, NameTokens.CMD_MashDownload, MashDownloadCommandHandler.class, MashDownloadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_BatchChange, NameTokens.CMD_BatchChange, BatchChangeCommandHandler.class, BatchChangeCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_BatchChangeMfgpart, NameTokens.CMD_BatchChangeMfgpart, BatchChangeMfgpartCommandHandler.class, BatchChangeMfgpartCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_DataRelease, NameTokens.CMD_DataRelease, DataReleaseCommandHandler.class, DataReleaseCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_SingleApplyData, NameTokens.CMD_SingleApplyData, SingleApplyDataCommandHandler.class, SingleApplyDataCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_TlbUpload, NameTokens.CMD_TlbUpload, TlbUploadCommandHandler.class, TlbUploadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_PartRoleUpload, NameTokens.CMD_PartRoleUpload, PartRoleUploadCommandHandler.class, PartRoleUploadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_RoleCfgUpload, NameTokens.CMD_RoleCfgUpload, RoleCfgUploadCommandHandler.class, RoleCfgUploadCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_BatchDownloadBOM, NameTokens.CMD_BatchDownloadBOM, BatchDownloadBOMCommandHandler.class, BatchDownloadBOMCommandDisplay.class );
        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_UPLoadABPART, NameTokens.CMD_UPLoadABPART, UploadABPartCommandHandler.class,  UploadABPartCommandDisplay.class );
        
        CommandsExtensionPointHelper.declareCommandGroupCommands( binder(), NameTokens.CMD_H3C_UPLOAD_GROUP );

        CommandsExtensionPointHelper.registerCommandHandler( binder(), NameTokens.CMD_H3C_UPLOAD_GROUP,
                NameTokens.CMD_H3C_UPLOAD_GROUP, UploadCommandGroupHandler.class, UploadCommandGroupDisplay.class );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(),
        		NameTokens.UploadMainFrame_SUB_LOCATION,
                com.siemens.splm.clientfx.ui.published.NameTokens.ONE_STEP_COMMANDS,
                UploadGroupCommandIdProvider.class, 10270 );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                BOMUploadCommandIdProvider.class,  BOMUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                BOMUploadCommandIdProvider.class,  BOMUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                LstUploadCommandIdProvider.class,  LstUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                NotesToPDMCommandIdProvider.class,  NotesToPDMCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                MashDownloadCommandIdProvider.class,  MashDownloadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                BatchChangeCommandIdProvider.class,  BatchChangeCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                BatchChangeMfgpartCommandIdProvider.class,  BatchChangeMfgpartCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                DataReleaseCommandIdProvider.class,  DataReleaseCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                SingleApplyDataCommandIdProvider.class,  SingleApplyDataCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                TlbUploadCommandIdProvider.class,  TlbUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                PartRoleUploadCommandIdProvider.class,  PartRoleUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                RoleCfgUploadCommandIdProvider.class,  RoleCfgUploadCommandKey.class );
        
        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                BatchDownloadBOMCommandIdProvider.class,  BatchDownloadBOMCommandKey.class );
       

        CommandsExtensionPointHelper.bindCommandToCommandGroup( binder(),
                NameTokens.CMD_H3C_UPLOAD_GROUP,
                UploadABPartCommandIdProvider.class,  UploadABPartCommandKey.class );

        // Adds the new command as a ToolsAndInfo command
//        CommandsExtensionPointHelper.contributeCommandToArea( binder(), com.siemens.splm.clientfx.tcui.xrt.published.NameTokens.objectNavigationSubLocation,com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,H3cUploadsModule.NotesToPDMCommandIdProvider.class, 1000 );

     
    }
    
    public static class UploadGroupCommandIdProvider
   	implements Provider<CommandId>
   	{
   		@Override
	    public CommandId get()
	    {
	        return new CommandId( NameTokens.CMD_H3C_UPLOAD_GROUP );
	    }
   	}
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class BOMUploadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_BOMUpload );
        }
    }
    
    public static class BOMUploadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10000 ) );
       }
   }
    
    /**
     * CommandID provider for LstUpload Command
     */
    public static class LstUploadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_LstUpload );
        }
    }
    
    public static class LstUploadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10010 ) );
       }
   }
    
    /**
     * CommandID provider for NotesToPDM Command
     */
    public static class NotesToPDMCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_NotesToPDM );
        }
    }
    
    public static class NotesToPDMCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10020 ) );
       }
   }
    
    /**
     * CommandID provider for NotesToPDM Command
     */
    public static class MashDownloadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_MashDownload );
        }
    }
    
    public static class MashDownloadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10030 ) );
       }
   }
    
    /**
     * CommandID provider for BatchChange Command
     */
    public static class BatchChangeCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_BatchChange );
        }
    }
    
    public static class BatchChangeCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10040 ) );
       }
   }
    
    /**
     * CommandID provider for BatchChangeMfgpart Command
     */
    public static class BatchChangeMfgpartCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_BatchChangeMfgpart );
        }
    }
    
    public static class BatchChangeMfgpartCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10050 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class DataReleaseCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_DataRelease );
        }
    }
    
    public static class DataReleaseCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10060 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class SingleApplyDataCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_SingleApplyData );
        }
    }
    
    public static class SingleApplyDataCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10070 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class TlbUploadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_TlbUpload );
        }
    }
    
    public static class TlbUploadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10080 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class PartRoleUploadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_PartRoleUpload );
        }
    }
    
    public static class PartRoleUploadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10090 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class RoleCfgUploadCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_RoleCfgUpload );
        }
    }
    
    public static class RoleCfgUploadCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10100 ) );
       }
   }
    
    

    /**
     * CommandID provider for BOMUpload Command
     */
    public static class UploadABPartCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_UPLoadABPART );
        }
    }
    

    public static class UploadABPartCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10120 ) );
       }
   }
    
    /**
     * CommandID provider for BOMUpload Command
     */
    public static class BatchDownloadBOMCommandIdProvider
        implements Provider<CommandId>
    {
        @Override
        public CommandId get()
        {
            return new CommandId( NameTokens.CMD_BatchDownloadBOM );
        }
    }
    
    public static class BatchDownloadBOMCommandKey
    implements Provider<CommandInCommandGroupKey>
   {
       @Override
       public CommandInCommandGroupKey get()
       {
           return new CommandInCommandGroupKey( Integer.valueOf( 10110 ) );
       }
   }
    
    /**
     * Bind the command widgets for favorites sublocation
     *
     * @param sublocationNameToken sublocation name token
     */
    private void bindUploadsCommandWidgets( String sublocationNameToken )
    {
//        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
//                com.siemens.splm.clientfx.ui.published.NameTokens.DISPLAY_COMMANDS,
//                TcUIXRTCommands.ModelObjectListDisplayTogglesCommandIdProvider.class, 100 );
//
//        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
//                com.siemens.splm.clientfx.ui.published.NameTokens.DISPLAY_COMMANDS,
//                TcUIXRTCommands.ShowSelectionSummaryCommandIdProvider.class, 200 );

        // Register the oneStep Commands
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.BOMUploadCommandIdProvider.class, 100 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.LstUploadCommandIdProvider.class, 200 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.NotesToPDMCommandIdProvider.class, 300 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.MashDownloadCommandIdProvider.class, 350 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.BatchChangeCommandIdProvider.class, 400 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.BatchChangeMfgpartCommandIdProvider.class, 500 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.DataReleaseCommandIdProvider.class, 800 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.SingleApplyDataCommandIdProvider.class, 850 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.TlbUploadCommandIdProvider.class, 900 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.PartRoleUploadCommandIdProvider.class, 1000 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.RoleCfgUploadCommandIdProvider.class, 1100 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.BatchDownloadBOMCommandIdProvider.class, 1200 );
        
        CommandsExtensionPointHelper.contributeCommandToArea( binder(), sublocationNameToken,
                com.siemens.splm.clientfx.ui.published.NameTokens.TOOLS_AND_INFO_COMMANDS,
                H3cUploadsModule.UploadABPartCommandIdProvider.class, 1110 );

    }
    

    /**
     */
    public static class UploadMainFrameSubLocationContribution
        extends DefaultSubLocationContribution
    {
        /**
         * Constructor
         * 
         * @param H3ReportNavFrameSubLocationPresenter H3ReportNavFrameSubLocationPresenter
         */
        @Inject
        public UploadMainFrameSubLocationContribution(
                AsyncProvider<UploadMainFrameSubLocationPresenter> h3ReportNavFrameSubLocationPresenter )
        {
            super( UploadMainFrameSubLocationPresenter.PRIORITY, H3cUploadsMessages.INSTANCE.UploadMainFrameTitle(),
                    NameTokens.UploadMainFrame_SUB_LOCATION, h3ReportNavFrameSubLocationPresenter );
        }
    }
    
   
}
