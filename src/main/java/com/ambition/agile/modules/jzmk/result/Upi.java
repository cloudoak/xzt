package com.ambition.agile.modules.jzmk.result;

public class Upi extends Result{
	/*@Override
	protected void work()
    {
        int[] sc = compute_common_gene();
        int sum = sc[4];
        int lie = sc[5];

        //测谎
        if ((sum >= 25 && lie >= 3) || (sum <= 5 && lie <= 1))
        {
            guide = String.format("总分：<span class=score>%s</span>分，说谎：<span class=score>%s</span>分，掩饰性高，结果不可信。",
                                  sum, lie);
            state = 5;
            //return;
        }

        //解释
        DataTable dt = Tools.ExecuteDataTable(con,
                                              "SELECT question,explain,advice FROM upi WHERE id IN (SELECT qnumber FROM test_result WHERE rid=" +
                                              rid + " AND anumber=1 AND qnumber<=60)");
        if (dt.Rows.Count > 0)
        {
            StringBuilder sb = new StringBuilder("<ul>");
            foreach (DataRow dr in dt.Rows)
            {
                sb.AppendFormat("<li>【选择】{0}<br />\r\n", dr[0]);
                sb.AppendFormat("【解释】{0}<br />\r\n", dr[1]);
                sb.AppendFormat("【建议】{0}</li>\r\n", dr[2]);
            }
            sb.AppendLine("</ul>");
            explain = sb.ToString();
        }

        DataTable dt2 = Tools.ExecuteDataTable(con,
                                               "SELECT anumber FROM test_result WHERE rid=" + rid +
                                               " ORDER BY qnumber");
        var score = new int[69];
        int i = 1;
        foreach (DataRow dr in dt2.Rows)
        {
            score[i++] = Convert.ToInt32(dr[0]) == 1 ? 1 : 0;
        }

        //正常异常判断
        object o = Tools.ExecuteScalar(con, "SELECT answer FROM upi_last_answer WHERE rid=" + rid);
        
         * 	'UPI筛选标准
            '第一类(8)
            '0. 总分在25分（包括25分）以上者；
            '1. 第25题做肯定选择者；
            '2. 第8、16、26题中有至少2题做肯定选择；
            '3. 辅助题(65、66和67题)中有至少2题做肯定选择者；
            '4. 明确提出咨询要求且属于心理问题者(68题)。
            '第二类(9)
            '5. 总分在20～24分之间者；
            '6. 第8、16、26题中只有一题作肯定选择者；
            '7. 辅助题(65、66和67题)中只有一题作肯定选择者；
         * 
        int _class = 3;
        //第一类筛选
        if ((sum >= 25) || (score[25] == 1) || (score[8] + score[16] + score[26] >= 2) ||
            (score[65] + score[66] + score[67] >= 2) || (o != null && o.ToString().Length > 10))
        {
            abnormal = true;
            Tools.ExecuteNonQuery(con, "UPDATE test_score SET abnormal=1 WHERE gid=100000238 AND rid=" + rid);
            _class = 1;
        }
        if (_class == 3)
        {
            //第二类筛选
            if ((sum >= 20 && sum <= 24) || (score[8] + score[16] + score[26] == 1) ||
                (score[65] + score[66] + score[67] == 1))
            {
                abnormal = true;
                Tools.ExecuteNonQuery(con, "UPDATE test_score SET abnormal=1 WHERE gid=100000238 AND rid=" + rid);
                _class = 2;
            }
        }
        Tools.ExecuteNonQuery(con,
                              String.format(
                                  "DELETE FROM upi_class WHERE rid=%d;INSERT INTO upi_class(rid,class) VALUES(%d,%d)",
                                  rid,rid, _class));
    }*/
}
