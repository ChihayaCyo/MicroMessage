        var index = 1;
        function add() {
            $("#contents").after(
				"<td width='90' align='right'>内容"+ index +"：</td>"+
				"<td><input name='content"+ index +"' type='text' class='allInput' value='${content }'/></td></tr>"
                );
            index += 1;
        }