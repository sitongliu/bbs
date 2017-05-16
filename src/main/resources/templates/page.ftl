<div class="pagination">
    <ul>
        <li>
            
                <a href="article?action=queryall&cur=0">首页</a>
                <#if page.number!=0>
                    <a href="article?action=queryall&cur=${page.number-1}">前一页</a>
                <#--<#else>

                    <a href="">前一页</a>-->
                </#if>
			
        </li>
    <#list 0..page.totalPages-1 as i>
        <li>
        <a href="article?action=queryall&cur=${i}">${i+1}</a>
        </li>
    </#list>


    	<li>
                <#if page.number!=page.totalPages-1>
				    <a href="article?action=queryall&cur=${page.number+1}">下一页</a>
               <#-- <#else>
                    <a href="#">下一页</a>-->
                </#if>
			
        </li>

    	<li>
			

					<a href="article?action=queryall&cur=${page.totalPages-1}">尾页</a>
			

			

        </li>

    </ul>
</div>