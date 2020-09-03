// @<COPYRIGHT>@
// ==================================================
// Copyright 2015.
// Siemens Product Lifecycle Management Software Inc.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.h3c.uploads.internal.views;

import java.util.HashMap;
import java.util.Map;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnChangeUploaderHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.h3c.uploads.internal.uihandlers.UploadABPartUiHandlers;
import com.siemens.splm.clientfx.base.published.MessageUtil;
import com.siemens.splm.clientfx.base.published.config.IFxBaseGinjector;
import com.siemens.splm.clientfx.kernel.clientmodel.published.IJSO;
import com.siemens.splm.clientfx.kernel.clientmodel.published.IJsArray;
import com.siemens.splm.clientfx.kernel.clientmodel.published.JSOFactory;
import com.siemens.splm.clientfx.kernel.published.KernelGinjector;
import com.siemens.splm.clientfx.kernel.soa.published.ISOAResponse;
import com.siemens.splm.clientfx.kernel.soa.published.SOA;
import com.siemens.splm.clientfx.tcui.session.published.SessionService;

/**
 * View for NotesToPDM.
 */
public class UploadABPartView extends ViewWithUiHandlers<UploadABPartUiHandlers> implements com.h3c.uploads.internal.presenters.UploadABPartPresenterW.MyView, IUploader.OnFinishUploaderHandler,
		OnChangeUploaderHandler
{

	/**
	 * File upload form
	 */
	@UiField
	SingleUploader2 fileUploader = new SingleUploader2();
	
	
	@UiField
	TextArea text = new TextArea();
	
	@UiField
	Button btnUpload;
	
	private Map attrMaps = new HashMap<String,String>();

	/**
	 * Widget
	 */
	private final Widget m_widget;

	public interface Binder extends UiBinder<Widget, UploadABPartView>
	{
		//
	}
	/**
	 * Constructor
	 * 
	 * @param binder
	 *            Ui binder
	 */
	@Inject
	public UploadABPartView(final Binder binder)
	{
		m_widget = binder.createAndBindUi(this);
		
		text.setHeight("600px");
		
		text.setReadOnly(true);
		
		
		fileUploader.setAutoSubmit(false);
		fileUploader.addOnChangeUploadHandler(this);
		fileUploader.setValidExtensions("xlsx", "XLSX");
		
		fileUploader.setWidth("400px");
		
		fileUploader.addOnFinishUploadHandler(this);
//		fileUploader.setMaximumFiles(1);
		fileUploader.setFileInputPrefix("default");

		// You can add customized parameters to servlet call
		System.out.println("defaultUploader.getServletPath():" + fileUploader.getServletPath());
		// defaultUploader.setServletPath(defaultUploader.getServletPath() +
		// "?foo=bar");
		String baseUrl = KernelGinjector.INSTANCE.getSession().getEndPointDefinition().getBaseURL();
		String page = baseUrl + "upload";
		fileUploader.setServletPath(page);
		fileUploader.avoidRepeatFiles(true);
		// This enables php apc progress mechanism
//		fileUploader.add(new Hidden("APC_UPLOAD_PROGRESS", fileUploader.getInputName()));
	}

	@Override
	public Widget asWidget()
	{
		return m_widget;
	}

	@UiHandler("btnUpload")
	public void onClick(ClickEvent event)
	{
		if(fileUploader.getFileName() != null && fileUploader.getFileName().length() > 0)
		{
			
			
				text.setText("正在上载...");
				fileUploader.submit();
				String fileName = fileUploader.getInputName();
				getUiHandlers().showMessage("fileName:" + fileName);

				btnUpload.setEnabled(false);
				btnUpload.setText("请稍候...");
			
			
		}
	}

	@Override
	public void onFinish(IUploader uploader)
	{
		

		  //Part编码
		attrMaps.put("Part编码", "s1PartNumber");
//	Part分类
		attrMaps.put("Part分类", "s1PartFamilyType");
//	分类代码
		attrMaps.put("分类代码", "s1PartFamilyCode");
//	分类名称
		attrMaps.put("分类名称", "s1PartFamilyName");
//	产品线
		attrMaps.put("产品线", "H3ProductLine");
//	产品
		attrMaps.put("产品", "H3Product");
//	产品V版本
		attrMaps.put("产品V版本", "ProjectName");
//	来源
		attrMaps.put("来源", "MakeBuyIndicator");
//	项目描述
		attrMaps.put("项目描述", "s1PartDescElements");
//	对内英文描述
		attrMaps.put("对内英文描述", "s1IntEnglishDesc");
//	对外中文描述
		attrMaps.put("对外中文描述", "s1ExtChineseDesc");
//	对外英文描述
		attrMaps.put("对外英文描述", "s1ExtEnglishDesc");
//	产品代码
		attrMaps.put("产品代码", "s1ExternalModel");
//	对内型号
		attrMaps.put("对内型号", "s1InternalModel");
//	Part模板
		attrMaps.put("Part模板", "s1PartTemplate");
//	计量单位
		attrMaps.put("计量单位", "h3c_uom_str");
//	Part状态
		attrMaps.put("Part状态", "s1PartStatus");
//	Part版本
		attrMaps.put("Part版本", "s1PartRevision");
//	是否单独对外报价
		attrMaps.put("是否单独对外报价", "s1ExternalQuote");
//	报价件类别
		attrMaps.put("报价件类别", "s1QuotedPartType");
//	是否OEM OUT产品
	
		attrMaps.put("是否OEM OUT产品", "s1IsOEMOUTProduct");
//	OEM OUT厂家
		attrMaps.put("OEM OUT厂家", "s1OEMOUTManufatrer");
//	原型机编码（对OEM OUT
		attrMaps.put("原型机编码（对OEM OUT）", "s1PrototypeCode");
//	是否HP 4*4物料
		attrMaps.put("HP CPL", "s2HPCPL");
		//
		attrMaps.put("是否HP 4*4物料", "H3IsHP4x4");
		
//	是否涉及进口许可证
		attrMaps.put("是否涉及进口许可证", "s1ImportLicense");
//	是否允许直发
		attrMaps.put("是否允许直发", "s2IsAllowSend");
//	Part优选等级
		attrMaps.put("Part优选等级", "s1PriorityLevel");
//	发布日期
		attrMaps.put("发布日期", "ActualRelDate");
//	BOM生效日期
		attrMaps.put("BOM生效日期", "ActualEffDate");
//	相关申请单号
		attrMaps.put("相关申请单号", "s2RelateReqDocNum");

//	特殊发放
		attrMaps.put("特殊发放", "s1hSpectialAttr");
//	原理图符号名称
		attrMaps.put("原理图符号名称", "s1SchSymbolFileName");
//	PCB封装库名称
		attrMaps.put("PCB封装库名称", "s1PcbEncpFileName");
//	是否有套片
		attrMaps.put("是否有套片", "s1HasKitPart");
//	是否预发布
		attrMaps.put("是否预发布", "s1IsUpdatedByTQC");
//	责任人
		attrMaps.put("责任人", "h3c_RespParty");
//	创建日期
		attrMaps.put("创建日期", "creation_date");
//	修改日期
		attrMaps.put("修改日期", "last_mod_date");
		// This is a must! Without this line, Excel content will not be re-read between operations
		fileUploader.reset();
		
		String userName = Cookies.getCookie("userName");
		String password = Cookies.getCookie("password");
		SessionService.signIn(userName, //
				password, //
				"", // no group input //$NON-NLS-1$
				"", // no role input //$NON-NLS-1$
				"", //
				"", //
				new AsyncCallback<ISOAResponse>()
				{
					@Override
					public void onFailure(Throwable caught)
					{
						MessageUtil.reportException(caught);
						btnUpload.setEnabled(true);
						btnUpload.setText("确定");
					}

					@Override
					public void onSuccess(ISOAResponse result)
					{
						
						String type = "UPLOAD_AB_PARTNUMBER";
						IJSO request = JSOFactory.createObject();					
						
						IJsArray input = JSOFactory.createArray();
						String[] lines = fileUploader.getServerMessage().getMessage().split(UploadCommon.LINE_SEPERATOR);
						if(lines.length < 2)
						{
							MessageUtil.reportException(new Exception("文件中没有有效内容。"));
							btnUpload.setEnabled(true);
							btnUpload.setText("确定");
						}
						
						int columnCount = 0;
						for(String value : lines[0].split(UploadCommon.VALUE_SEPERATOR))
						{
							if(value != null && value.length() > 0)
								columnCount++;
						}
						
//						
//						btnUpload.setEnabled(true);
//						btnUpload.setText("确定");
//						IJsArray input = JSOFactory.createArray();
//						String[] lines = fileUploader.getServerMessage().getMessage().split(UploadCommon.LINE_SEPERATOR);
//						
//						// check blank lines
//						boolean hasBlankLines = false;
//						String blankLines = "";
//						for(int i = 0; i < lines.length ; i++)
//						{
//							String line = lines[i];
//							if(line.length() == 0)
//							{
//								hasBlankLines = true;
//								if(blankLines.length() > 0)
//									blankLines += ", ";
//								blankLines += (i+1);
//							}
//							else
//							{
//								String[] cellValues = line.split(UploadCommon.VALUE_SEPERATOR);
//								if(cellValues[0] == null || cellValues[0].length() == 0)
//								{
//									hasBlankLines = true;
//									if(blankLines.length() > 0)
//										blankLines += ", ";
//									blankLines += (i+1);
//								}
//							}						
//							
//						}						
//						if(hasBlankLines) // warn and terminate
//						{
//							text.setText("");
//							IFxBaseGinjector.INSTANCE.getMessageService().warn("模板中第  " + blankLines + " 行为空行。 ");
//							return;
//						}
						
//						String lineT=lines[0];
//						String[] cellValuesT=lineT.split(UploadCommon.VALUE_SEPERATOR);
//						
//							if(!cellValuesT[0].equals("Part编码"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'part编码'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[1].equals("项目描述"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'项目描述'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[2].equals("Part分类"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'Part分类'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[3].equals("厂家"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'厂家'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[4].equals("厂家型号"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'厂家型号'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[5].equals("更改内容"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'更改内容'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[6].equals("名称"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'名称'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[7].equals("更改前值"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'更改前值'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[8].equals("更改后值"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'更改后值'列 对应不上。 ");
//								return;
//							}
//							if(!cellValuesT[9].equals("属性填写正确性校验及内部属性名"))
//							{
//								text.setText("");
//								IFxBaseGinjector.INSTANCE.getMessageService().warn("上传excel和模板'属性填写正确性校验及内部属性名'列 对应不上。 ");
//								return;
//							}
//						
//							
//						
//						for(int i = 1; i < lines.length ; i++)
//						{
//							String line = lines[i];
//							if(line.length() == 0)
//								continue;
//						    
//							String[] cellValues = line.split(UploadCommon.VALUE_SEPERATOR);							
//							IJSO lineData = JSOFactory.createObject();							
//							lineData.setString("partNumber", cellValues[0]);
//							lineData.setString("projectDesc", cellValues.length > 1 ? cellValues[1] : "");
//							lineData.setString("partFmCode", cellValues.length > 2 ? cellValues[2] : "");
//							lineData.setString("factory", cellValues.length > 3 ? cellValues[3] : "");
//							lineData.setString("factoryNum", cellValues.length > 4 ? cellValues[4] : "");
//							lineData.setString("changeCont", cellValues.length > 5 ? cellValues[5] : "");
//							lineData.setString("attrName", cellValues.length > 6 ? cellValues[6] : "");
//							lineData.setString("oldValue", cellValues.length > 7 ? cellValues[7] : "");
//							lineData.setString("newValue", cellValues.length > 8 ? cellValues[8] : "");
//							lineData.setString("attrCheckAndName", cellValues.length > 9 ? cellValues[9] : "");
//							
//							input.push(lineData);
//						}				
//						
//						IJSO request = JSOFactory.createObject();
//						request.setArray("input", input);
//						request.setString("applicationID", applicationID.getText());
//

						btnUpload.setEnabled(false);
						btnUpload.setText("请稍候...");
						String[] keys = lines[0].split(UploadCommon.VALUE_SEPERATOR);
						System.out.println("lines[0]:"+lines[0]);
						IJsArray data = JSOFactory.createArray();
						for(int i = lines.length-1; i>=1; i--)
						{
							IJSO lineObj = JSOFactory.createObject();
							Map<String, String> lineData = new HashMap<String, String>();
							String line = lines[i];
							if(line.length() == 0)
								continue;
							
							String[] cellValues = line.split(UploadCommon.VALUE_SEPERATOR);
							for(int j = 0; j < keys.length; j++)
							{
								
								lineData.put((String) attrMaps.get(keys[j]), j < cellValues.length ? cellValues[j] : "");
								
							}

							System.out.println("lineData:"+lineData);
							lineObj.setMapString2String("inputDataMap", lineData);
							data.push(lineObj);
						}										
						
						request.setString("opType", type);
						request.setArray("dataMaps", data);
						
							
						SOA.executeOperation("H3Upload-2015-10-ApplyData", "doApplyData", request, new AsyncCallback<ISOAResponse>()
						{
							@Override
							public void onFailure(Throwable caught)
							{
								MessageUtil.reportException(caught);
								btnUpload.setEnabled(true);
								btnUpload.setText("确定");	
							}

							@Override
							public void onSuccess(ISOAResponse response)
							{
								boolean isOK = response.getBoolean("isOK");
								String infoMsg = response.getString("infoMsg");
								String errorMsg = response.getString("errMsg");
								
								StringBuilder sb = new StringBuilder();
								sb.append(isOK ? "上载成功。" : "上载失败。").append("\n\n");
								
								if(errorMsg != null && errorMsg.length() > 0)
								{
									sb.append("错误信息:\n");
									
									sb.append(errorMsg);
									sb.append("\n");
									
								}
								
								if(infoMsg != null && infoMsg.length() > 0)
								{
									sb.append("警告信息:\n");
										
									sb.append(infoMsg);
									sb.append("\n");
								}
								
								

								text.setText(sb.toString());
								
								// Only with this expression, will fileUploader's onChangeHandler take effect 
								fileUploader.reset();
								btnUpload.setEnabled(true);
								btnUpload.setText("确定");	
							}
						});
						
					}
				});

		if (uploader.getStatus() == Status.SUCCESS)
		{
			
		}

	}
	
	@Override
	public void onChange(IUploader uploader)
	{
		text.setText("");
		
		if(uploader.getFileInput().getFilename().contains("（")
				|| uploader.getFileInput().getFilename().contains("）"))
		{
			IFxBaseGinjector.INSTANCE.getMessageService().warn("文件名中请不要包含中文括号。");
		}
		btnUpload.setEnabled(true);
		btnUpload.setText("确定");
	}

}
