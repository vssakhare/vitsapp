
<!-- <div class="pagination_holder"  align="right">
	<div class="pagination"> -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="pagination_holder_big">
			<tr>
				<td align="center" valign="middle"><div id="newTotalRecords"><%=totalRecordsStr%></div></td>
				<td align="center" valign="middle">Go to page: 
					<input name="pageNo" type="text" class = "textfield_micro" onkeypress = "checkKeyCode(event)" />
						
						<%
							if ((hasPrevious != null && hasPrevious.equals("true")) || (hasNext!= null && hasNext.equals("true")))
							{
						%>
						<a href ="javascript:pagingNavigation('go')">
						<img id = "goButton" src="<%=ApplicationConstants.IMAGE_PATH%>btn_go.gif" align="absmiddle" class="go_btn"/></a> 

						<%
							}
							else
							{
						%>
							<img src="<%=ApplicationConstants.IMAGE_PATH%>btn_go.gif" align="absmiddle" class="go_btn" />
						<%}%>
				</td>
				<td align="right" valign="middle">
					<!-- <div class="pagination">
						<div class="pagination_text"> -->
							<label>
								<%
									if (hasPrevious != null && hasPrevious.equals("true"))
									{
								%>
									<a href = "javascript:pagingNavigation('previous')">
									<img src="<%=ApplicationConstants.IMAGE_PATH%>icon_arrow_left.gif" hspace="3" align="absmiddle" border = "0" /></a>
								<%
									}
									else
									{
								%>
									<img src="<%=ApplicationConstants.IMAGE_PATH%>icon_arrow_left.gif" hspace="3" align="absmiddle" />
								<%}%>

								<%=displayStr%>
								
								<%
									if (hasNext != null && hasNext.equals("true"))
									{
								%>
									<a href = "javascript:pagingNavigation('next')">
									<img src="<%=ApplicationConstants.IMAGE_PATH%>icon_arrow_right.gif" hspace="3" align="absmiddle" border = "0"/></a>
								<%
									}
									else
									{
								%>
									<img src="<%=ApplicationConstants.IMAGE_PATH%>icon_arrow_right.gif" hspace="3" align="absmiddle" />	
								<%}%>

							</label>
						<!-- </div>
					</div> -->
				</td>
			</tr>
		</table>
	<!-- </div>
</div> -->
