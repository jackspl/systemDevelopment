// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal;

/**
 * Name tokens used in H3cUploads module
 */
public interface NameTokens
{
    /** Uploads location token. */
    String Uploads_LOCATION = "com.h3c.uploads.Uploads"; //$NON-NLS-1$

    /** UploadMainFrame token. */
    String UploadMainFrame_SUB_LOCATION = "com.h3c.uploads.UploadMainFrameSubLocation"; //$NON-NLS-1$

    /** UploadMainFrame history token. */
    String UploadMainFrame_TOKEN = "h3cUploads"; //$NON-NLS-1$
    
    String CMD_H3C_UPLOAD_GROUP = "com.h3c.upload.group";

    /** Command constant. */
    /**
     * BOM上载
     */
    String CMD_BOMUpload = "com.h3c.uploads.H3cUploads.BOMUpload"; //$NON-NLS-1$
    
    /**
     * lst/rpt/txt文件上载
     */
    String CMD_LstUpload = "com.h3c.uploads.H3cUploads.LstUpload"; //$NON-NLS-1$
    
    /**
     * Notes2PDM批量导入/硬件文件批量导入/外部文档批量导入/研发过程文档/研发过程文档包批量导入
     */
    String CMD_NotesToPDM = "com.h3c.uploads.H3cUploads.NotesToPDM"; //$NON-NLS-1$
    
    /**
     * 批量下载器件资料
     */
    String CMD_MashDownload = "com.h3c.uploads.H3cUploads.MashDownload"; //$NON-NLS-1$
    
    /**
     * 批量处理更改
     */
    String CMD_BatchChange = "com.h3c.uploads.H3cUploads.BatchChange"; //$NON-NLS-1$
    
    /**
     * 批量删除厂家型号及资料
     */
    String CMD_BatchChangeMfgpart = "com.h3c.uploads.H3cUploads.BatchChangeMfgpart"; //$NON-NLS-1$
        
    /**
     * 数据对外发放批量上载
     */
    String CMD_DataRelease = "com.h3c.uploads.H3cUploads.DataRelease"; //$NON-NLS-1$
    
    /**
     * 批量处理单独数据申请
     */
    String CMD_SingleApplyData = "com.h3c.uploads.H3cUploads.SingleApplyData"; //$NON-NLS-1$
    
    /**
     * TLB跟踪编码批量导入
     */
    String CMD_TlbUpload = "com.h3c.uploads.H3cUploads.TlbUpload"; //$NON-NLS-1$
    
    /**
     * Part小类的角色配置批量导入
     */
    String CMD_PartRoleUpload = "com.h3c.uploads.H3cUploads.PartRoleUpload"; //$NON-NLS-1$
    
    /**
     * 产品V版本的角色配置批量导入
     */
    String CMD_RoleCfgUpload = "com.h3c.uploads.H3cUploads.RoleCfgUpload"; //$NON-NLS-1$
    
    /**
     * 批量下载BOM清单
     */
    String CMD_BatchDownloadBOM = "com.h3c.uploads.H3cUploads.BatchDownloadBOM"; //$NON-NLS-1$
   
    /**
     * 上载AB编码
     */
    String CMD_UPLoadABPART = "com.h3c.uploads.H3cUploads.UploadABPart"; //$NON-NLS-1$

}
