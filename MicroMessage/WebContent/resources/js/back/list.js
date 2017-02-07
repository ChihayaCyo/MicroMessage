/**
 * 调用后台批量删除方法
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action",basePath + "DeleteBatch.action");
	$("#mainForm").submit();
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

/**
 * 单条删除post方法
 */
function deleteOne(basePath,id) {
	$("#mainForm").attr("action",basePath + "DeleteOne.action?id="+id);
	$("#mainForm").submit();
}



/**
 * 调用后台单条修改方法
 */
function alertList(basePath,id) {
	$("#mainForm").attr("action",basePath + "AlterList.action?id=" + id);
	$("#mainForm").submit();
}


/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage){
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}






