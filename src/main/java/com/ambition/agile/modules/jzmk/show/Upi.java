package com.ambition.agile.modules.jzmk.show;

public class Upi extends TestView{
	/*@Override
	protected  void Draw()
    {
        //取出因子分数
        DataTable dt = Tools.ExecuteDataTable(con, String.format(
                                                       "SELECT score FROM test_score WHERE rid=%d ORDER BY gid",
                                                       rid));
        int[] sc = new int[dt.Rows.Count];
        int j = 0;
        foreach (DataRow dr in dt.Rows)
        {
            sc[j++] = Convert.ToInt32(dr[0]);
        }
        int sum = sc[4];
        int lie = sc[5];

        if ((sum >= 25 && lie >= 3) || (sum <= 5 && lie <= 1))
        {
            Response.Redirect("test_done.aspx?rid="+rid+"&t=1");
            return;
        }
        //取出原始分数
        DataTable dt2 = Tools.ExecuteDataTable(con,
                                               "SELECT anumber FROM test_result WHERE rid=" + rid +
                                               " AND qnumber IN (8,16,25,26,5,20,35,50) ORDER BY qnumber");
        var score = new int[dt2.Rows.Count];
        j = 0;
        foreach (DataRow dr in dt2.Rows)
        {
            score[j++] = Convert.ToInt32(dr[0]) == 1 ? 1 : 0;
        }

        //得分详细说明
        StringBuilder sb = new StringBuilder("<table width='100%' border='0' cellspacing='0' class='stat-tb'>");
        sb.append(
            "<tr><th class='stat-td1' rowspan='2'>项 目</th><th class='stat-td1' rowspan='2'>总分</th><th class='stat-td1' rowspan='2'>躯体症状</th><th class='stat-td1' colspan='3'>精神症状</th><th class='stat-td1' colspan='4'>关键项目</th><th class='stat-td2' colspan='4'>测伪题</th></tr><tr><th class='stat-td1'>精神分裂</th><th class='stat-td1'>抑郁症</th><th class='stat-td1'>神经症</th>");
        int[] q = {8, 16, 25, 26, 5, 20, 35, 50};
        for (int i = 0; i < 8; i++)
        {
            sb.append("<th class='stat-td%d'>%d</th>\r\n", i == 7 ? 2 : 1, q[i]);
        }
        sb.append(
            "</tr><tr><th class='stat-td1' align='center'>分 数</th><td class='stat-td1' align='center'>%d</td>\r\n",
            sum);
        for (int i = 0; i < 4; i++)
        {
            sb.append("<td class='stat-td1' align='center'>%d</td>", sc[i]);
        }
        for (int i = 0; i < 8; i++)
        {
            sb.AppendFormat("<td class='stat-td{0}' align='center'>{1}</td>\r\n", i == 7 ? 2 : 1,
                            score[i] == 0 ? "&nbsp;" : "1");
        }
        sb.append("</tr></table>\r\n");
        Literal5.Text = sb.toString();
    }*/
}
