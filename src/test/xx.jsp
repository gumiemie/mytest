<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>商机订阅-邮件内容</title>
    <style type="text/css">
        .clearfix:after {
            content: '';
            display: block;
            clear: both;
            visibility: hidden;
            line-height: 0;
            height: 0;
            font-size: 0;
        }

        .clearfix {
            *zoom: 1;
        }
    </style>
</head>

<body style="background:#fbfbfb; margin:0; padding:0;font-family:'\5FAE\8F6F\96C5\9ED1', Arial, Helvetica, sans-serif;">
<table width="788" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <img src="${boPath}/images/adImg.jpg">
            <div style="width:788px; border:solid #efefef 6px; margin:0 auto; position:relative;background:#fff;">
                <div style="width: 100%;height:54px; background:#efefef">
                    <img src="${boPath}/images/nlogo.png" width="200" height="36" alt="image"
                         style="margin:5px 0 0 28px;float: left;">
                    <%if (!isHistoryView) {%>
                    <span style="float: right;margin-top: 18px;color: #888888;font-size: 12px;">如果邮件不能正常显示，请<a
                            href="${boPath}/busiopport/historyView.htm?code=${code}" target="_blank"
                            style="margin: 0px 5px;color: #1e6dff;text-decoration: none">点击此处</a>查看</span>
                    <% } %>
                </div>
                <div style="padding:37px 26px">
                    <p style="font-size:16px; margin-bottom:20px;">尊敬的&nbsp;${name} ，您好！</p>
                    <p style="font-size:15px; color: #444;line-height:20px;margin-bottom: 20px;">
                        您在${createTimeBeginStr}到${createTimeStr}订阅的商机信息共计：<b style="color: #ff4800">${sjCount}</b>条 </p>
                    <%
                        if (!isSimple) {
                    %>
                    <div style="background:#fafafa; border:solid #f5f5f5 1px;border-left:solid #e9dcd1 4px; padding:20px;">
                        <h1 style="font-size:14px; color:#666;">当前订阅条件</h1>
                        <div class="clearfix" style="font-family:'宋体'; margin-bottom:10px;">
                            <span style="float:left; width: 58px;text-align:right;font-size:12px; color:#666; ">关键词：</span>
                            <div style="display:inline-block; float:left; width:630px;">

                                <%
                                    for (keyword in keywords) {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:5px">${keyword}</a>
                                <%
                                    if (!keywordLP.last) {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:5px">|</a>
                                <%
                                            }
                                        }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">招标类型：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    for (doc in bidModels
                                    !
                                    )
                                    {
                                %>
                                <span style="margin-right:5px" id='biddingType'>${doc}</span>
                                <%}%>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅类型：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    for
                                    (
                                    info
                                    in
                                    infoClass
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${info.infoClassName}</a>
                                <%
                                    if
                                    (
                                    !
                                    infoLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%}%>
                                <%}%>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅行业：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    if
                                    (
                                    isEmpty
                                    (
                                    industrys
                                    )
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">全部行业</a>
                                <%
                                    }
                                    else
                                    {
                                    for
                                    (
                                    indu
                                    in
                                    industrys
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${indu}</a>
                                <%
                                    if
                                    (
                                    !
                                    induLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%
                                    }
                                    }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅地区：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    if
                                    (
                                    isEmpty
                                    (
                                    provinces
                                    )
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">全国</a>
                                <%
                                    }
                                    else
                                    {
                                    for
                                    (
                                    prov
                                    in
                                    provinces
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${prov}</a>
                                <%
                                    if
                                    (
                                    !
                                    provLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%
                                    }
                                    }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <%
                        else
                        {
                    %>

                    <div style="background:#fafafa; border:solid #f5f5f5 1px;border-left:solid #e9dcd1 4px; padding:20px;">
                        <h1 style="font-size:14px; color:#666;">当前订阅条件</h1>
                        <div class="clearfix" style="font-family:'宋体'; margin-bottom:10px;">
                            <span style="float:left; width: 58px;text-align:right;font-size:12px; color:#666;">关键词：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    for
                                    (
                                    keyword
                                    in
                                    keywords
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:5px">${keyword}</a>
                                <%
                                    if
                                    (
                                    !
                                    keywordLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:5px">|</a>
                                <%
                                    }
                                    }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">招标类型：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    for
                                    (
                                    doc
                                    in
                                    bidModels
                                    !
                                    )
                                    {
                                    if
                                    (
                                    docLP
                                    .
                                    size
                                    >
                                    1
                                    )
                                    {
                                %>
                                <span style="margin-right:5px">全部</span>
                                <%
                                    break
                                    ;
                                    }
                                %>
                                <span style="margin-right:5px" id='biddingType'>${doc}</span>
                                <%}%>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅类型：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    for
                                    (
                                    info
                                    in
                                    infoClass
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${info.infoClassName}</a>
                                <%
                                    if
                                    (
                                    !
                                    infoLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%}%>
                                <%}%>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅行业：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    if
                                    (
                                    isEmpty
                                    (
                                    industrys
                                    )
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">全部行业</a>
                                <%
                                    }
                                    else
                                    {
                                    for
                                    (
                                    indu
                                    in
                                    industrys
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${indu}</a>
                                <%
                                    if
                                    (
                                    !
                                    induLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%
                                    }
                                    }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="clearfix" style="font-family:'宋体';margin-bottom:10px;">
                            <span style="float:left; font-size:12px; color:#666; padding-top:4px;">订阅地区：</span>
                            <div style="display:inline-block; float:left; width:610px;">
                                <%
                                    if
                                    (
                                    isEmpty
                                    (
                                    provinces
                                    )
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">全国</a>
                                <%
                                    }
                                    else
                                    {
                                    for
                                    (
                                    prov
                                    in
                                    provinces
                                    !
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none; margin-right:10px">${prov}</a>
                                <%
                                    if
                                    (
                                    !
                                    provLP
                                    .
                                    last
                                    )
                                    {
                                %>
                                <a href="javascript:;"
                                   style="font-size:12px; color:#576a8d; text-decoration:none;margin-right:10px">|</a>
                                <%
                                    }
                                    }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
                <%
                    if
                    (
                    !
                    isHistoryView
                    )
                    {
                %>
                <div style="height: 200px;margin-top: 35px;padding:37px 26px;border-top: 1px #efefef solid;">
                    <span style="padding-left: 24px;font-size: 14px;color: #666666;background: url(${hadesPath}/images/questionIcon.png) no-repeat 2px;">常见问题</span>
                    <ul class="clearfix" style="font-size: 12px;color: #888888">
                        <li style="float: left;width: 50%;list-style-type: square;margin-bottom: 7px;">
                            <span>总收不到邮件，</span>
                            <a href="${boPath}/html/busiopport/mail_custom_index.jsp#/useSimpleVersion" target="_blank"
                               style="margin-left: 5px;color: #1e6dff;text-decoration: none">请订阅简易版</a>
                        </li>
                        <li style="float: left;width: 50%;list-style-type: square;margin-bottom: 7px;">
                            <span>无商机不打扰，</span>
                            <a href="${boPath}/html/busiopport/mail_custom_index.html#/mailSet/${userKey}"
                               target="_blank" style="margin-left: 5px;color: #1e6dff;text-decoration: none">去设置</a>
                        </li>
                        <li style="float: left;width: 50%;list-style-type: square">
                            <span>修改订阅条件，</span>
                            <a href="${boPath}/html/busiopport/mail_custom_index.jsp" target="_blank"
                               style="margin-left: 5px;color: #1e6dff;text-decoration: none">请登录必联网</a>
                        </li>
                        <li style="float: left;width: 50%;list-style-type: square">
                            <span>不想收到邮件，</span>
                            <a href="${boPath}/html/busiopport/mail_custom_index.jsp" target="_blank"
                               style="margin-left: 5px;color: #1e6dff;text-decoration: none">去退订</a>
                        </li>
                        <li class="clearfix"
                            style="float: left;width: 90%;padding-top: 16px;margin-left: -15px;list-style-type: none">
                            如有任何问题欢迎致电诚信投会员服务热线：<b>027-59558858</b></li>
                    </ul>
                </div>
                <% } %>

            </div>

        </td>
    </tr>
</table>
</body>
</html>