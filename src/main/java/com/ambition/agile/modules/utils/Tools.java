package com.ambition.agile.modules.utils;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ambition.agile.common.utils.FreeMarkers;

import freemarker.template.Configuration;
import freemarker.template.Template;

/// <summary>
    /// Tools 的摘要说明。
    /// </summary>
    public class Tools
    {
    	


    	
        //学校结构树
        private static final String[] consultway_arr = {"面询", "网上咨询", "电话咨询"};
        private static final String[] orderway_arr = {"当面", "电话", "网上"};
        private static final String[] status_arr = {"未处理", "接受", "取消", "已结束", "空闲"};

        public static String[] NationList = {
                                                "汉族", "回族", "藏族", "满族", "朝鲜族", "蒙古族", "壮族", "瑶族", "彝族", "阿昌族", "白族",
                                                "布依族", "傣族", "侗族", "土家族", "独龙族", "仡佬族", "哈尼族", "基诺族", "景颇族", "拉祜族", "黎族",
                                                "傈僳族", "珞巴族", "毛南族", "门巴族", "苗族", "仫佬族", "纳西族", "怒族", "普米族", "羌族", "畲族",
                                                "水族", "维吾尔族", "哈萨克族", "塔塔尔族", "土族", "东乡族", "撒拉族", "保安族", "鄂伦春族", "鄂温克族","达斡尔族",
                                                 "赫哲族", "乌兹别克族", "柯尔克孜族", "锡伯族", "裕固族", "布朗族", "德昂族", "佤族", "塔吉克斯坦族", "俄罗斯族", "高山族",
                                                "京族"
                                            };

        /* filter HTML Special Character*/

//        public static String filterHTML(String s)
//        {
//            if (s == null) return "";
//            //s = s.Trim();
//            //s = s.Replace("\"", "&quot;");
//            //s = s.Replace("&", "&amp;");
//            s = s.Replace("<", "&lt;");
//            s = s.Replace(">", "&gt;");
//            s = s.Replace("'", "''");
//            return s;
//        }
//
//        //获取课件分类
//        public static void GetCourseType(ref DropDownList DropDownList1)
//        {
//            //分类下拉
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT id,Type FROM CourseType").DefaultView;
//            DropDownList1.DataTextField = "Type";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//        /// <summary>
//        /// 获取咨询问题类型
//        /// </summary>
//        /// <param name="DropDownList1">DropDownList</param>
//        public static void GetConsultType(ref DropDownList DropDownList1)
//        {
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT id,ConsultType FROM ConsultType").DefaultView;
//            DropDownList1.DataTextField = "ConsultType";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//        /// <summary>
//        /// 获取咨询师列表
//        /// </summary>
//        /// <param name="DropDownList1">DropDownList</param>
//        public static void GetConsultPeople(ref DropDownList DropDownList1)
//        {
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT id,Name FROM Teacher WHERE id IN (SELECT teacherID FROM ConsultPeople)").
//                    DefaultView;
//            DropDownList1.DataTextField = "Name";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//
//        /// <summary>
//        /// 过滤单引号
//        /// </summary>
//        /// <param name="s">要过滤的字符串</param>
//        /// <returns></returns>
//        public static String filter4Sql(String s)
//        {
//            if (s == null) return "";
//            s = s.Trim();
//            s = s.Replace("'", "''");
//            return s;
//        }
//
//        /// <summary>
//        /// 上传文件,返回上传后的文件名
//        /// </summary>
//        /// <param name="File1">HtmlInputFile</param>
//        /// <param name="base_path">根目录</param>
//        /// <returns></returns>
//        public static String UploadFile(ref HtmlInputFile File1, String base_path)
//        {
//            if (File1.PostedFile.ContentLength == 0) return "";
//            if (File1.Value == "") return "";
//            String file_name = File1.PostedFile.FileName;
//            String file_time = DateTime.Now.Year + DateTime.Now.Month.toString() + DateTime.Now.Day +
//                               DateTime.Now.Hour + DateTime.Now.Second + DateTime.Now.Minute +
//                               DateTime.Now.Millisecond;
//            file_name = file_time + GetRandomint() +
//                        file_name.SubString(file_name.IndexOf("."), file_name.Length - file_name.IndexOf("."));
//            File1.PostedFile.SaveAs(base_path + "\\upload\\" + file_name);
//            return file_name;
//        }
//        
//        public static String getUploadFileName()
//        {
//            return  DateTime.Now.Year + DateTime.Now.Month.toString() + DateTime.Now.Day +
//                               DateTime.Now.Hour + DateTime.Now.Second + DateTime.Now.Minute +
//                               DateTime.Now.Millisecond + GetRandomint();
//        }
//
//        /// <summary>
//        /// 生成随机数
//        /// </summary>
//        /// <returns></returns>
//        public static String GetRandomint()
//        {
//            var random = new Random();
//            return (random.Next(10000).toString());
//        }
//
//        /// <summary>
//        /// 获取教师名单
//        /// </summary>
//        /// <param name="DropDownList1"></param>
//        public static void GetTeacherList(ref DropDownList DropDownList1)
//        {
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT id,name FROM Teacher WHERE IsUsed=1 AND IsChecked=1");
//            DropDownList1.DataTextField = "name";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//        public static void BindDdl(String sqlstr,DropDownList ddl,String datatextfield, String datavaluefield)
//        {
//            ddl.DataSource = ExecuteDataTable(sqlstr);
//            ddl.DataTextField = datatextfield;
//            ddl.DataValueField = datavaluefield;
//            ddl.DataBind();
//        }
//
//        /// <summary>
//        /// 获取返回首页
//        /// </summary>
//        /// <param name="base_path">虚拟程序根路径</param>
//        /// <param name="refer">请求页URL</param>
//        /// <returns></returns>
//        public static String GetDefaultPage(String base_path, String refer)
//        {
//            return base_path + "/default.aspx";
//        }
//
//        /// <summary>
//        /// 获取部门列表	
//        /// </summary>
//        /// <param name="DropDownList1"></param>
//        public static void GetDepartmentList(ref DropDownList DropDownList1)
//        {
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT id,departmentname FROM Department").DefaultView;
//            DropDownList1.DataTextField = "departmentname";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//        /// <summary>
//        /// 获取教师类型列表
//        /// </summary>
//        /// <param name="DropDownList1">DropDownList</param>
//        public static void GetTeacherTypeList(ref DropDownList DropDownList1)
//        {
//            DropDownList1.DataSource =
//                ExecuteDataTable("SELECT * FROM TeacherType").DefaultView;
//            DropDownList1.DataTextField = "TypeName";
//            DropDownList1.DataValueField = "id";
//            DropDownList1.DataBind();
//        }
//
//
//        /// <summary>
//        /// 获取测评任务编号列表
//        /// </summary>
//        /// <param name="DropDownList1"></param>
//        public static void GetMissionList(ref DropDownList DropDownList1)
//        {
//            DataTable dt = ExecuteDataTable("SELECT mcode,mid FROM test_mission WHERE isdone=1 AND isdel=0");
//            DropDownList1.DataSource = dt.DefaultView;
//            DropDownList1.DataTextField = "mcode";
//            DropDownList1.DataValueField = "mid";
//            DropDownList1.DataBind();
//        }
//
//        /// <summary>
//        /// 获取量表列表
//        /// </summary>
//        /// <param name="DropDownList1"></param>
//        public static void GetTableList(ref DropDownList DropDownList1)
//        {
//            DataTable dt = ExecuteDataTable("SELECT tid,tname FROM test_table WHERE isDone=1");
//            DropDownList1.DataSource = dt.DefaultView;
//            DropDownList1.DataTextField = "tname";
//            DropDownList1.DataValueField = "tid";
//            DropDownList1.DataBind();
//        }
//
//        /// <summary>
//        /// 从session里获取_Teacher对象
//        /// </summary>
//        /// <param name="session">Session</param>
//        /// <param name="request"></param>
//        /// <returns>_Teacher对象</returns>
//        public static _Teacher GetTeacherFromSession(HttpSessionState session, HttpRequest request)
//        {
//            User user = GetUserFromSession(session, request);
//            ;
//            if (user == null) return null;
//            if (user.Type == User.TEACHER) return (_Teacher) user;
//            return null;
//        }
//
//        /// <summary>
//        /// 从session获取User对象
//        /// </summary>
//        /// <param name="session">Session</param>
//        /// <returns>User对象</returns>
//        public static User GetUserFromSession(HttpSessionState session, HttpRequest request)
//        {
//            const String DB_SESSION_LOCK_KEY = "db_session_lock";
//            if (session[User.SESSION_KEY] == null) //session里没有?
//            {
//                User user;
//                if (request.Cookies["xzt_user"] != null) //cookies里有?
//                {
//                    String session_id = filterHTML(request.Cookies["xzt_user"].Value);
//
//                    //先检查是否锁住,即其他进程访问此处
//                    if (session[DB_SESSION_LOCK_KEY] != null) //锁住了？
//                    {
//                        while (true)
//                        {
//                            System.Threading.Thread.Sleep(100);//等待
//                            if (session[DB_SESSION_LOCK_KEY]==null)
//                            {
//                                return session[User.SESSION_KEY]!=null?(User)session[User.SESSION_KEY]:null;
//                            }
//                        }
//                    }
//                    //加锁
//                    session.Add(DB_SESSION_LOCK_KEY,1);
//
//                    String sql =
//                        String.Format(
//                            "SELECT * FROM AllUsers_v WHERE uid=(SELECT uid FROM session WHERE session_id='{0}' AND DATEDIFF(second,getdate(),end_time)>=0 AND user_host_name='{1}')",
//                            session_id,filterHTML(request.UserHostName));
//                    DataTable dt = ExecuteDataTable(sql);
//                    if (dt.Rows.Count == 0)
//                    {
//                        session.Remove(DB_SESSION_LOCK_KEY);
//                        return null;
//                    }
//                    if (session[User.SESSION_KEY] != null) return (User) session[User.SESSION_KEY];
//                    //重新填充User对象
//                    int utype = int.Parse(dt.Rows[0]["utype"].toString().Trim());
//                    String uid = dt.Rows[0]["uid"].toString().Trim();
//                    if (utype == User.TEACHER) //教师?
//                    {
//                        user = new _Teacher();
//                        user.ID = uid;
//                        ((_Teacher)user)._getPermit();
//                    }
//                    else
//                        user = new User();
//                    user.ID = uid;
//                    user.Age = dt.Rows[0]["Age"]==DBNull.Value?0:Convert.ToInt32(dt.Rows[0]["Age"]);
//                    user.Integral = dt.Rows[0]["integral"]==DBNull.Value?0:Convert.ToInt32(dt.Rows[0]["integral"]);
//                    user.Password = dt.Rows[0]["password"].toString().Trim();
//                    user.Sex = dt.Rows[0]["sex"].toString().Trim();
//                    user.Type = utype;
//                    user.UserName = dt.Rows[0]["username"].toString().Trim();
//                    user.Name = dt.Rows[0]["name"]==DBNull.Value?"":dt.Rows[0]["name"].toString().Trim();
//                    //放进session
//                    session.Add(User.SESSION_KEY, user);
//                    session.Remove(DB_SESSION_LOCK_KEY);
//                    //刷新
//                    //sql = String.Format("UPDATE session SET session_id='{0}' WHERE session_id='{1}'", session.SessionID,
//                    //                    session_id);
//                    //request.Cookies["xzt_user"].Value = session.SessionID;
//                }
//                else
//                    return null;                
//            }
//            return (User) session[User.SESSION_KEY];
//        }
//
//        /// <summary>
//        /// 返回错误页url
//        /// </summary>
//        /// <param name="errorno">错误代码</param>
//        /// <param name="back_url">返回地址</param>
//        /// <returns>错误页url</returns>
//        public static String GetErrorPagePath(long errorno, String back_url)
//        {
//            //TODO:参数检查
//            return String.Format("~/error.aspx?errno={0}&b={1}", errorno, Base64Encode(back_url));
//        }
//
//        /// <summary>
//        /// 返回错误页url,返回地址为上个请求url
//        /// </summary>
//        /// <param name="Request">HttpRequest</param>
//        /// <param name="errorno">错误代码</param>
//        /// <returns></returns>
//        public static String GetErrorPagePath(HttpRequest Request, long errorno)
//        {
//            if (Request.UrlReferrer == null)
//            {
//                return GetErrorPagePath(errorno, "");
//            }
//            return GetErrorPagePath(errorno, Request.UrlReferrer.AbsoluteUri);
//        }
//
//        /// <summary>
//        /// 返回错误页url,返回地址为当前目录某个页
//        /// </summary>
//        /// <param name="Request">HttpRequest</param>
//        /// <param name="errorno">错误代码</param>
//        /// <param name="file_name">当前目录某个页</param>
//        /// <returns></returns>
//        public static String GetErrorPagePath(HttpRequest Request, long errorno, String file_name)
//        {
//            return GetErrorPagePath(errorno, GetParentPath(Request.Path) + "/" + file_name);
//        }
//
//        /// <summary>
//        /// Base64编码
//        /// </summary>
//        /// <param name="str">字符串</param>
//        /// <returns></returns>
//        public static String Base64Encode(String str)
//        {
//            var Base64Code =
//                new[]
//                    {
//                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
//                        't', 'u', 'v', 'w',
//                        'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//                        'Q', 'R', 'S', 'T',
//                        'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='
//                    };
//            byte empty = 0;
//            var byteMessage = new ArrayList(Encoding.Default.GetBytes(str));
//            int messageLen = byteMessage.Count;
//            int page = messageLen/3;
//            int use = 0;
//            if ((use = messageLen%3) > 0)
//            {
//                for (int i = 0; i < 3 - use; i++)
//                    byteMessage.Add(empty);
//                page++;
//            }
//            StringBuilder outmessage = new StringBuilder(page*4);
//            for (int i = 0; i < page; i++)
//            {
//                var instr = new byte[3];
//                instr[0] = (byte) byteMessage[i*3];
//                instr[1] = (byte) byteMessage[i*3 + 1];
//                instr[2] = (byte) byteMessage[i*3 + 2];
//                var outstr = new int[4];
//                outstr[0] = instr[0] >> 2;
//
//                outstr[1] = ((instr[0] & 0x03) << 4) ^ (instr[1] >> 4);
//                if (!instr[1].Equals(empty))
//                    outstr[2] = ((instr[1] & 0x0f) << 2) ^ (instr[2] >> 6);
//                else
//                    outstr[2] = 64;
//                if (!instr[2].Equals(empty))
//                    outstr[3] = (instr[2] & 0x3f);
//                else
//                    outstr[3] = 64;
//                outmessage.Append(Base64Code[outstr[0]]);
//                outmessage.Append(Base64Code[outstr[1]]);
//                outmessage.Append(Base64Code[outstr[2]]);
//                outmessage.Append(Base64Code[outstr[3]]);
//            }
//            return outmessage.toString();
//        }
//
//        /// <summary>
//        /// Base64解码		
//        /// </summary>
//        /// <param name="str">Base64 String</param>
//        /// <returns></returns>
//        public static String Base64Decode(String str)
//        {
//            if ((str.Length%4) != 0)
//            {
//                return null;
//            }
//            if (!Regex.IsMatch(str, "^[A-Z0-9/+=]*$", RegexOptions.IgnoreCase))
//            {
//                return null;
//            }
//            const String Base64Code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/=";
//            int page = str.Length/4;
//            var outMessage = new ArrayList(page*3);
//            char[] message = str.ToCharArray();
//            for (int i = 0; i < page; i++)
//            {
//                var instr = new byte[4];
//                instr[0] = (byte) Base64Code.IndexOf(message[i*4]);
//                instr[1] = (byte) Base64Code.IndexOf(message[i*4 + 1]);
//                instr[2] = (byte) Base64Code.IndexOf(message[i*4 + 2]);
//                instr[3] = (byte) Base64Code.IndexOf(message[i*4 + 3]);
//                var outstr = new byte[3];
//                outstr[0] = (byte) ((instr[0] << 2) ^ ((instr[1] & 0x30) >> 4));
//                if (instr[2] != 64)
//                {
//                    outstr[1] = (byte) ((instr[1] << 4) ^ ((instr[2] & 0x3c) >> 2));
//                }
//                else
//                {
//                    outstr[2] = 0;
//                }
//                if (instr[3] != 64)
//                {
//                    outstr[2] = (byte) ((instr[2] << 6) ^ instr[3]);
//                }
//                else
//                {
//                    outstr[2] = 0;
//                }
//                outMessage.Add(outstr[0]);
//                if (outstr[1] != 0)
//                    outMessage.Add(outstr[1]);
//                if (outstr[2] != 0)
//                    outMessage.Add(outstr[2]);
//            }
//            var outbyte = (byte[]) outMessage.ToArray(Type.GetType("System.Byte"));
//            return Encoding.Default.GetString(outbyte);
//        }
//
//        /// <summary>
//        /// Get parent folder,not end with '/'
//        /// </summary>
//        /// <param name="url">url</param>
//        /// <returns>folder url</returns>
//        public static String GetParentPath(String url)
//        {
//            int index = url.LastIndexOf('/');
//            return index >= 0 ? url.SubString(0, index) : url;
//        }
//
//        /// <summary>
//        /// 是否管理此班级
//        /// </summary>
//        /// <param name="teacher_id">教师ID</param>
//        /// <param name="class_id">班级ID</param>
//        /// <returns>是否管理此班级</returns>
//        public static Boolean ClassAllow(String teacher_id, long class_id)
//        {
//            String sql = String.Format("SELECT COUNT(*) FROM teacher_class WHERE teacher_id={0} AND class_id={1}",
//                                       teacher_id, class_id);
//            int count = int.Parse(ExecuteScalar(sql).toString());
//            return count > 0;
//        }
//
//        /// <summary>
//        /// 判断学生是否在负责班级里
//        /// </summary>
//        /// <param name="teacher_id">教师ID</param>
//        /// <param name="student_code">学号</param>
//        /// <returns></returns>
//        public static Boolean ClassAllow(String teacher_id, String student_code)
//        {
//            String sql =
//                String.Format(
//                    "SELECT COUNT(*) FROM teacher_class WHERE teacher_id={0} AND class_id=(SELECT classid FROM StudentInfo WHERE Code='{1}')",
//                    teacher_id, student_code);
//            int count = int.Parse(ExecuteScalar(sql).toString());
//            return count > 0;
//        }
//
//        /// <summary>
//        /// 执行sql,不返回结果集
//        /// </summary>
//        /// <param name="sql">sql</param>
//        /// <returns>影响的行</returns>
//        public static int ExecuteNonQuery(String sql)
//        {
//            return SqlHelper.ExecuteNonQuery(Global.DB_CON, CommandType.Text, sql);
//        }
//
//        public static int ExecuteNonQuery(SqlConnection conn,String sql)
//        {
//            return SqlHelper.ExecuteNonQuery(conn, CommandType.Text, sql);
//        }
//
//        /// <summary>
//        /// 执行存储过程,不返回结果集
//        /// </summary>
//        /// <param name="spName">sql过程</param>
//        /// <param name="parameterValues">影响的行</param>
//        /// <returns></returns>
//        public static int ExecuteNonQuery(String spName, params SqlParameter[] parameterValues)
//        {
//            return SqlHelper.ExecuteNonQuery(Global.DB_CON, CommandType.StoredProcedure, spName, parameterValues);
//        }
//
//        public static int ExecuteNonQuery(SqlConnection conn,String spName, params SqlParameter[] parameterValues)
//        {
//            return SqlHelper.ExecuteNonQuery(conn, CommandType.StoredProcedure, spName, parameterValues);
//        }
//
//        /// <summary>
//        /// 执行SQL,返回标量
//        /// </summary>
//        /// <param name="conn"></param>
//        /// <param name="sql">sql</param>
//        /// <returns>标量</returns>
//        public static object ExecuteScalar(SqlConnection conn,String sql)
//        {
//            return SqlHelper.ExecuteScalar(conn, CommandType.Text, sql);
//        }
//
//        public static object ExecuteScalar(String sql)
//        {
//            return SqlHelper.ExecuteScalar(Global.DB_CON, CommandType.Text, sql);
//        }
//
//        public static object ExecuteScalar(String sql, params SqlParameter[] parameterValues)
//        {
//            return SqlHelper.ExecuteScalar(Global.DB_CON, CommandType.Text, sql, parameterValues);
//        }
//
//        /// <summary>
//        /// 执行SQL,返回第一张表结果
//        /// </summary>
//        /// <param name="sql">sql</param>
//        /// <returns>DataTable</returns>
//        public static DataTable ExecuteDataTable(String sql)
//        {
//            return SqlHelper.ExecuteDataset(Global.DB_CON, CommandType.Text, sql).Tables[0];
//        }
//
//        public static DataTable ExecuteDataTable(SqlConnection conn,String sql)
//        {
//            return SqlHelper.ExecuteDataset(conn, CommandType.Text, sql).Tables[0];
//        }
//
//        /// <summary>
//        /// 执行一个存储过程，返回结果集
//        /// </summary>
//        /// <param name="spName">存储过程名</param>
//        /// <param name="parameterValues">参数</param>
//        /// <returns></returns>
//        public static DataSet ExecuteDataTable(String spName, params object[] parameterValues)
//        {
//            return SqlHelper.ExecuteDataset(Global.DB_CON, spName, parameterValues);
//        }
//
//        /// <summary>
//        /// 返回咨询方式
//        /// </summary>
//        /// <param name="index">索引号</param>
//        /// <returns></returns>
//        public static String GetConsultWay(int index)
//        {
//            if (index < 0 || index >= consultway_arr.Length) return "";
//            return consultway_arr[index];
//        }
//
//        /// <summary>
//        /// 返回预约方式
//        /// </summary>
//        /// <param name="index">索引号</param>
//        /// <returns></returns>
//        public static String GetOrderWay(int index)
//        {
//            if (index < 0 || index > orderway_arr.Length) return "";
//            return orderway_arr[index];
//        }
//
//        /// <summary>
//        /// 返回预约处理状态
//        /// </summary>
//        /// <returns>索引号</returns>
//        public static String GetOrderStatus(int index)
//        {
//            if (index < 0 || index > status_arr.Length) return "";
//            return status_arr[index];
//        }
//
//        /// <summary>
//        /// 获取民族名称
//        /// </summary>
//        /// <param name="index">索引号</param>
//        /// <returns></returns>
//        public static String GetNation(int index)
//        {
//            if (index < 0 || index >= NationList.Length) return null;
//            return NationList[index];
//        }
//
//
//        /// <summary>
//        /// 布尔转换
//        /// </summary>
//        /// <param name="o"></param>
//        /// <returns></returns>
//        public static Boolean IsTrue(object o)
//        {
//            try
//            {
//                return Boolean.Parse(o.toString());
//            }
//            catch
//            {
//                return false;
//            }
//        }
//
//        /// <summary>
//        /// 转换int
//        /// </summary>
//        /// <param name="o"></param>
//        /// <param name="default_v"></param>
//        /// <returns></returns>
//        public static int ParseInt(object o, int default_v)
//        {
//            try
//            {
//                return int.Parse(o.toString());
//            }
//            catch
//            {
//                return default_v;
//            }
//        }

        /// <summary>
        /// 生成档案
        /// </summary>
        /// <param name="uid">用户ID</param>
        /// <param name="option">档案内容</param>
        /// <returns>档案内容 word2003 xml</returns>
       /* public static String GetArchive(long uid, String option)
        {
            String sql = String.format("SELECT utype FROM AllUsers_v2 WHERE uid=%s", uid);
            Object outype = 0;//ExecuteScalar(sql);
            if (outype == null) return "";
            int utype = Integer.parseInt(outype.toString());
            switch (utype)
            {
                case 0:
                    return GetTeacherArchive(uid, option);
                case 1:
                    return GetStudentArchive(uid, option);
                case 2:
                    return GetParentArchive(uid, option);
                default:
                    break;
            }
            return "";
        }*/

        /// <summary>
        /// 生成学生档案
        /// </summary>
        /// <param name="uid">用户ID</param>
        /// <param name="option">档案内容</param>
        /// <returns>档案内容 word2003 xml</returns>
        public static String GetStudentArchive(long uid, String option)
        {
        	StringBuilder buffer = new StringBuilder();
        	Map<String, Object> model = com.google.common.collect.Maps.newHashMap();
        	String result = ""; 
    		try {
    			Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
    			Template template = cfg.getTemplate("IssueSubmitFormTag.ftl");
    			result = FreeMarkers.renderTemplate(template, model);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            return buffer.toString();
        }
//            var con = new SqlConnection(Global.DB_CON);
//            con.Close();
//            //基本信息
//            String sql =
//                String.Format(
//                    "SELECT *,dbo.GetClassFullName(ClassID) AS ClassFullName FROM StudentInfo WHERE StudentID={0}", uid);
//            DataTable dt = ExecuteDataTable(con,sql);
//            if (dt.Rows.Count == 0)
//            {
//                con.Close();
//                return "用户不存在";
//            }
//            DataRow dr = dt.Rows[0];
//    String student_name = dr["Name"].toString();
    
            /*doc*/
                


//	<w:fonts>
//		<w:defaultFonts w:ascii=""Calibri"" w:fareast=""宋体"" w:h-ansi=""Calibri"" w:cs=""Times New Roman""/>
//		<w:font w:name=""Times New Roman"">
//			<w:panose-1 w:val=""02020603050405020304""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Roman""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""20002A87"" w:usb-1=""80000000"" w:usb-2=""00000008"" w:usb-3=""00000000"" w:csb-0=""000001FF"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""宋体"">
//			<w:altName w:val=""SimSun""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""黑体"">
//			<w:altName w:val=""SimHei""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Cambria Math"">
//			<w:panose-1 w:val=""02040503050406030204""/>
//			<w:charset w:val=""01""/>
//			<w:family w:val=""Roman""/>
//			<w:notTrueType/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000000"" w:usb-1=""00000000"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""00000000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Calibri"">
//			<w:panose-1 w:val=""020F0502020204030204""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Swiss""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""A00002EF"" w:usb-1=""4000207B"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""0000009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@宋体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@黑体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""楷体_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//
//		<w:font w:name=""仿宋_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@仿宋_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//	</w:fonts>
//	<w:styles>
//		<w:versionOfBuiltInStylenames w:val=""7""/>
//		<w:latentStyles w:defLockedState=""off"" w:latentStyleCount=""267"">
//			<w:lsdException w:name=""Normal""/>
//			<w:lsdException w:name=""heading 1""/>
//			<w:lsdException w:name=""heading 2""/>
//			<w:lsdException w:name=""heading 3""/>
//			<w:lsdException w:name=""heading 4""/>
//			<w:lsdException w:name=""heading 5""/>
//			<w:lsdException w:name=""heading 6""/>
//			<w:lsdException w:name=""heading 7""/>
//			<w:lsdException w:name=""heading 8""/>
//			<w:lsdException w:name=""heading 9""/>
//			<w:lsdException w:name=""caption""/>
//			<w:lsdException w:name=""Title""/>
//			<w:lsdException w:name=""Subtitle""/>
//			<w:lsdException w:name=""Strong""/>
//			<w:lsdException w:name=""Emphasis""/>
//			<w:lsdException w:name=""No Spacing""/>
//			<w:lsdException w:name=""List Paragraph""/>
//			<w:lsdException w:name=""Quote""/>
//			<w:lsdException w:name=""Intense Quote""/>
//			<w:lsdException w:name=""Subtle Emphasis""/>
//			<w:lsdException w:name=""Intense Emphasis""/>
//			<w:lsdException w:name=""Subtle Reference""/>
//			<w:lsdException w:name=""Intense Reference""/>
//			<w:lsdException w:name=""Book Title""/>
//			<w:lsdException w:name=""TOC Heading""/>
//		</w:latentStyles>
//		<w:style w:type=""paragraph"" w:default=""on"" w:styleId=""a"">
//			<w:name w:val=""Normal""/>
//			<wx:uiName wx:val=""正文""/>
//			<w:rsid w:val=""009D1BC2""/>
//			<w:pPr>
//				<w:widowControl w:val=""off""/>
//				<w:jc w:val=""both""/>
//			</w:pPr>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:kern w:val=""2""/>
//				<w:sz w:val=""21""/>
//				<w:sz-cs w:val=""22""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//		</w:style>
//		<w:style w:type=""character"" w:default=""on"" w:styleId=""a0"">
//			<w:name w:val=""Default Paragraph Font""/>
//			<wx:uiName wx:val=""默认段落字体""/>
//		</w:style>
//		<w:style w:type=""table"" w:default=""on"" w:styleId=""a1"">
//			<w:name w:val=""Normal Table""/>
//			<wx:uiName wx:val=""普通表格""/>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//			<w:tblPr>
//				<w:tblInd w:w=""0"" w:type=""dxa""/>
//				<w:tblCellMar>
//					<w:top w:w=""0"" w:type=""dxa""/>
//					<w:left w:w=""108"" w:type=""dxa""/>
//					<w:bottom w:w=""0"" w:type=""dxa""/>
//					<w:right w:w=""108"" w:type=""dxa""/>
//				</w:tblCellMar>
//			</w:tblPr>
//		</w:style>
//		<w:style w:type=""list"" w:default=""on"" w:styleId=""a2"">
//			<w:name w:val=""No List""/>
//			<wx:uiName wx:val=""无列表""/>
//		</w:style>
//	</w:styles>
//	<w:shapeDefaults>
//		<o:shapedefaults v:ext=""edit"" spidmax=""3074""/>
//		<o:shapelayout v:ext=""edit"">
//			<o:idmap v:ext=""edit"" data=""1""/>
//		</o:shapelayout>
//	</w:shapeDefaults>
//	<w:docPr>
//		<w:view w:val=""print""/>
//		<w:zoom w:percent=""100""/>
//		<w:doNotEmbedSystemFonts/>
//		<w:bordersDontSurroundHeader/>
//		<w:bordersDontSurroundFooter/>
//		<w:proofState w:spelling=""clean"" w:grammar=""clean""/>
//		<w:defaultTabStop w:val=""420""/>
//		<w:drawingGridVerticalSpacing w:val=""156""/>
//		<w:displayHorizontalDrawingGridEvery w:val=""0""/>
//		<w:displayVerticalDrawingGridEvery w:val=""2""/>
//		<w:punctuationKerning/>
//		<w:characterSpacingControl w:val=""CompressPunctuation""/>
//		<w:optimizeForBrowser/>
//		<w:validateAgainstSchema/>
//		<w:saveInvalidXML w:val=""off""/>
//		<w:ignoreMixedContent w:val=""off""/>
//		<w:alwaysShowPlaceholderText w:val=""off""/>
//		<w:compat>
//			<w:spaceForUL/>
//			<w:balanceSingleByteDoubleByteWidth/>
//			<w:doNotLeaveBackslashAlone/>
//			<w:ulTrailSpace/>
//			<w:doNotExpandShiftReturn/>
//			<w:adjustLineHeightInTable/>
//			<w:breakWrappedTables/>
//			<w:snapToGridInCell/>
//			<w:wrapTextWithPunct/>
//			<w:useAsianBreakRules/>
//			<w:dontGrowAutofit/>
//			<w:useFELayout/>
//		</w:compat>
//		<wsp:rsids>
//			<wsp:rsidRoot wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""000D577E""/>
//			<wsp:rsid wsp:val=""001A79FD""/>
//			<wsp:rsid wsp:val=""0028502F""/>
//			<wsp:rsid wsp:val=""0036319D""/>
//			<wsp:rsid wsp:val=""003F0D2E""/>
//			<wsp:rsid wsp:val=""00444D08""/>
//			<wsp:rsid wsp:val=""00505A6F""/>
//			<wsp:rsid wsp:val=""005117FA""/>
//			<wsp:rsid wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""0074124B""/>
//			<wsp:rsid wsp:val=""007C4307""/>
//			<wsp:rsid wsp:val=""007F6200""/>
//			<wsp:rsid wsp:val=""00862721""/>
//			<wsp:rsid wsp:val=""00876FC3""/>
//			<wsp:rsid wsp:val=""00915363""/>
//			<wsp:rsid wsp:val=""009A7475""/>
//			<wsp:rsid wsp:val=""009D1BC2""/>
//			<wsp:rsid wsp:val=""00A067D2""/>
//			<wsp:rsid wsp:val=""00A143AA""/>
//			<wsp:rsid wsp:val=""00A725CD""/>
//			<wsp:rsid wsp:val=""00BA1D51""/>
//			<wsp:rsid wsp:val=""00BE6A85""/>
//			<wsp:rsid wsp:val=""00C16355""/>
//			<wsp:rsid wsp:val=""00C635A3""/>
//			<wsp:rsid wsp:val=""00CF3752""/>
//			<wsp:rsid wsp:val=""00DA7BD9""/>
//			<wsp:rsid wsp:val=""00DC28CC""/>
//			<wsp:rsid wsp:val=""00DC534A""/>
//			<wsp:rsid wsp:val=""00E729B8""/>
//			<wsp:rsid wsp:val=""00E74C72""/>
//			<wsp:rsid wsp:val=""00EE1AAD""/>
//			<wsp:rsid wsp:val=""00EF78FF""/>
//			<wsp:rsid wsp:val=""00F02057""/>
//		</wsp:rsids>
//	</w:docPr>
//	<w:body>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>{1}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>学生心理档案</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>姓名</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    {2}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>性别</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00505A6F"" wsp:rsidRPr=""00505A6F"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{3}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""007A6A86"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>年龄</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t> </w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>   </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""0074124B"" wsp:rsidRPr=""0074124B"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{4}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>学号</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00862721"" wsp:rsidRPr=""00862721"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{5}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""0007707E"" wsp:rsidRDefault=""009A7475""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00EF78FF"">
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//				</w:rPr>
//				<w:t>{6}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1098""/>
//				<w:gridCol w:w=""936""/>
//				<w:gridCol w:w=""906""/>
//				<w:gridCol w:w=""1051""/>
//				<w:gridCol w:w=""1194""/>
//				<w:gridCol w:w=""129""/>
//				<w:gridCol w:w=""935""/>
//				<w:gridCol w:w=""696""/>
//				<w:gridCol w:w=""1577""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00876FC3"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>姓 名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>性 别</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年 龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""696"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00876FC3"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00876FC3"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{4}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//							</w:rPr>
//							<w:t>民</w:t>
//						</w:r>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//							</w:rPr>
//							<w:t> </w:t>
//						</w:r>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//							</w:rPr>
//							<w:t>族</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""00876FC3"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00876FC3"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{7}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>籍贯</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3015"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""00444D08"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00444D08"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{8}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>照</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学 号</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00444D08"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00444D08"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{5}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学 校</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3015"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""000D577E"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""000D577E"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>班 级</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""5757"" w:type=""dxa""/>
//						<w:gridSpan w:val=""7""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""000D577E"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""000D577E"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{9}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>片</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""622""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>通信地址</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""5757"" w:type=""dxa""/>
//						<w:gridSpan w:val=""7""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""001A79FD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""仿宋_UTF-8"" w:fareast=""仿宋_UTF-8""/>
//								<wx:font wx:val=""仿宋_UTF-8""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""001A79FD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""仿宋_UTF-8"" w:fareast=""仿宋_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""仿宋_UTF-8""/>
//							</w:rPr>
//							<w:t>{10}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>城镇</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""696"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidR=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{11}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2046"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>是否住宿生</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1346"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{12}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1669"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生干部</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{13}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>爱好</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{14}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>特长</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{15}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>童</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>成</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>长</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>经</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>历</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00CF3752"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{16}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>身体健康情况</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{17}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学业情况</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{18}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>奖</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:proofErr w:type=""gramStart""/>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>惩</w:t>
//						</w:r>
//						<w:proofErr w:type=""gramEnd""/>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>情</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:proofErr w:type=""gramStart""/>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>况</w:t>
//						</w:r>
//						<w:proofErr w:type=""gramEnd""/>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{19}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>自</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>我</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>评</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>价</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{20}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>单亲</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{21}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>与父母同住</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{22}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家庭排行</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{23}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>父亲姓名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00C635A3"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00C635A3"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{24}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC534A"" wsp:rsidP=""00DA7BD9"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00DC534A"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{25}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""008C60AE"" wsp:rsidRDefault=""00A725CD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>联系电话</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A725CD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A725CD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{26}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>文化程度</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""003F0D2E"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""003F0D2E"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{27}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职业</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""005117FA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""005117FA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{28}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职务</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""00A067D2"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A067D2"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{29}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>母亲姓名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{30}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00BE6A85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BE6A85"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{31}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>联系电话</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{32}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>文化程度</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{33}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职业</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{34}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""008559A7"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""008C60AE"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//								<w:sz-cs w:val=""24""/>
//							</w:rPr>
//							<w:t>职务</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{35}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00915363"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2809"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>直系亲属是否有病史</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{36}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4637"" w:type=""dxa""/>
//						<w:gridSpan w:val=""5""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>直</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>系</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>亲</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>属</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>病</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>史</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{37}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00915363"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家庭中人际关系气氛</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{38}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>亲朋好友基本情况</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>及联系方式</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{39}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>是否贫困生</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""8""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{40}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>");
//
//            #endregion doc
//
//            //说明:下面采用替换是不得已而为之
//            buffer = buffer.Replace("{0}", DateTime.Now.toString()); //0
//            buffer = buffer.Replace("{1}", filter_b(Global.sysconfig.SchoolName)); //1
//            buffer = buffer.Replace("{2}", filter_b(dr["Name"])); //2
//            buffer = buffer.Replace("{3}", filter_b(dr["Sex"])); //3
//            buffer = buffer.Replace("{4}", filter_b(dr["Age"])); //4
//            buffer = buffer.Replace("{5}", filter_b(dr["Code"])); //5
//            buffer = buffer.Replace("{6}", DateTime.Today.toString("yyyy-MM-d")); //6
//            buffer = buffer.Replace("{7}", GetNation(ParseInt(dr["Nation"], 0))); //7
//            buffer = buffer.Replace("{8}", filter_b(dr["Native"])); //8
//            buffer = buffer.Replace("{9}", filter_b(dr["ClassFullName"])); //9
//            buffer = buffer.Replace("{10}", filter_b(dr["Address"])); //10
//            buffer = buffer.Replace("{11}", IsTrue(dr["IsCity"]) ? "是" : "否"); //11
//            buffer = buffer.Replace("{12}", IsTrue(dr["IsLiveInSchool"]) ? "是" : "否"); //12
//            buffer = buffer.Replace("{13}", IsTrue(dr["IsSchoolLeader"]) ? "是" : "否"); //13
//            buffer = buffer.Replace("{14}", filter_b(dr["Hobby"])); //14
//            buffer = buffer.Replace("{15}", filter_b(dr["Speciality"])); //15
//            buffer = buffer.Replace("{16}", filter_b(dr["ComeExperience"])); //16
//            buffer = buffer.Replace("{17}", filter_b(dr["BodyWork"])); //17
//            buffer = buffer.Replace("{18}", filter_b(dr["SchoolWork"])); //18
//            buffer = buffer.Replace("{19}", filter_b(dr["RewardsWork"])); //19
//            buffer = buffer.Replace("{20}", filter_b(dr["SelfComment"])); //20
//            buffer = buffer.Replace("{21}", IsTrue(dr["IsSingleParent"]) ? "是" : "否"); //21
//            buffer = buffer.Replace("{22}", IsTrue(dr["IsLiveWithParents"]) ? "是" : "否"); //22
//            buffer = buffer.Replace("{23}", filter_b(dr["HomeOrder"])); //23
//            buffer = buffer.Replace("{24}", filter_b(dr["FatherName"])); //24
//            buffer = buffer.Replace("{25}", filter_b(dr["FatherAge"])); //25
//            buffer = buffer.Replace("{26}", filter_b(dr["FatherTel"])); //26
//            buffer = buffer.Replace("{27}", filter_b(dr["FatherEdu"])); //27
//            buffer = buffer.Replace("{28}", filter_b(dr["FatherOcc"])); //28
//            buffer = buffer.Replace("{29}", filter_b(dr["FatherDuty"])); //29
//            buffer = buffer.Replace("{30}", filter_b(dr["MotherName"])); //30
//            buffer = buffer.Replace("{31}", filter_b(dr["MotherAge"])); //31
//            buffer = buffer.Replace("{32}",filter_b( dr["MotherTel"])); //32
//            buffer = buffer.Replace("{33}", filter_b(dr["MotherEdu"])); //33
//            buffer = buffer.Replace("{34}", filter_b(dr["MotherOcc"])); //34
//            buffer = buffer.Replace("{35}",filter_b( dr["MotherDuty"])); //35
//            buffer = buffer.Replace("{36}", IsTrue(dr["IsIll"].toString()) ? "是" : "否"); //36
//            buffer = buffer.Replace("{37}", filter_b(dr["IllDatails"])); //37
//            buffer = buffer.Replace("{38}",filter_b( dr["OtherPeople"])); //38
//            buffer = buffer.Replace("{39}", filter_b(dr["Friends"])); //39
//            buffer = buffer.Replace("{40}", IsTrue(dr["IsPoor"]) ? "是" : "否"); //40
//            //);
//            if (option.IndexOf("1") >= 0) //测评记录?
//            {
//                sql =
//                    String.Format(
//                        "SELECT * FROM test_table_tested_view WHERE uid={0} ORDER BY rid",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//
//                //枚举
//                foreach (DataRow dr2 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 表头
//                        @"
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""800""/>
//				<w:gridCol w:w=""666""/>
//				<w:gridCol w:w=""3047""/>
//				<w:gridCol w:w=""1698""/>
//				<w:gridCol w:w=""2311""/>
//			</w:tblGrid><w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1466"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>量 表</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7056"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1466"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>测试时间</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3047"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1698"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>指导教师</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2311"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t></w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>",
//
//                        #endregion
//                        filter_b(dr2["tname"]),
//                        dr2["starttime"]);
//
//                    String explain = filter_explain(dr2["explain"].toString());
//
//                    //因子分及解释
//                    //结果解释
//                    
//                    buffer.AppendFormat(
//
//                        #region 结果解释
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>测</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>试</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>结</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>果</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:ind w:first-line-chars=""216"" w:first-line=""518""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>",
//
//                        #endregion
//                     explain);
//
//                    //教师意见
//                    buffer.AppendFormat(
//
//                        #region doc
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>师</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>评</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>语</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00915363"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>",
//
//                        #endregion
//               dr2["guide"]!=DBNull.Value? filter_b(dr2["guide"].toString()):"");
//                }
//            }
//
//            //学生学习记录
//            if (option.IndexOf("3") >= 0)
//            {
//                buffer.Append(
//
//                    #region 表头
//                    @"		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""001C7187"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""009A7475"" wsp:rsidP=""009A7475"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生</w:t>
//						</w:r>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学习</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""001C7187"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                sql =
//                    String.Format(
//                        "SELECT id,title,content,createdate FROM CourseGuestBook WHERE WriteID={0} AND course_id IS NULL",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                //枚举每个问题
//                foreach (DataRow dr7 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 问题
//                        @"
//					<w:p wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""009A7475"" wsp:rsidP=""001C7187"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>问题:</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""009A7475"" wsp:rsidP=""001C7187"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion
//                        filter_b(dr7["title"]), filter_b(student_name), filter_b(dr7["date_time"]), filter_b(dr7["content"]));
//                    //解答
//                    sql =
//                        String.Format(
//                            "SELECT title,dbo.GetUserName(WriteID) AS 'teacher_name',createdate,content FROM ConsultAnswer WHERE course_id={0}",
//                            dr7["ID"]);
//                    DataTable dt7 = ExecuteDataTable(con,sql);
//                    foreach (DataRow dr8 in dt7.Rows)
//                    {
//                        buffer.AppendFormat(
//
//                            #region 解答
//                            @"
//					<w:p wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""009A7475"" wsp:rsidP=""001C7187"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>问题:</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""009A7475"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""009A7475"" wsp:rsidP=""001C7187"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                            #endregion"
//                            filter_b(dr8["title"]), filter_b(dr8["teacher_name"]), dr8["createdate"], filter_b(dr8["content"]));
//                    }
//                }
//                buffer.Append(
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//                    );
//            }
//            //留言记录
//            if (option.IndexOf("4") >= 0)
//            {
//                sql = String.Format("SELECT ID,title,date_time,content FROM ConsultQuestion WHERE StudentID={0}", uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生网上问题留言记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                //枚举每个问题
//                foreach (DataRow dr4 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 问题
//                        @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>问题：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion
//                        filter_b(dr4["title"]), filter_b(student_name), dr4["date_time"], filter_b(dr4["content"]));
//                    //解答
//                    sql =
//                        String.Format(
//                            "SELECT title,dbo.GetUserName(TeacherID) AS 'teacher_name',date_time,content FROM ConsultAnswer WHERE question_id={0}",
//                            dr4["ID"]);
//                    DataTable dt4 = ExecuteDataTable(con,sql);
//                    foreach (DataRow dr5 in dt4.Rows)
//                    {
//                        buffer.AppendFormat(
//
//                            #region 解答
//                            @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>解答：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                            #endregion
//                            filter_b(dr5["title"]), filter_b(dr5["teacher_name"]), dr5["date_time"], filter_b(dr5["content"]));
//                    }
//                }
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//            //咨询记录
//            if (option.IndexOf("2") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(TeacherID) AS 'teacher_name',start_time,ConsultHistory FROM RecordOrder WHERE StudentID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                foreach (DataRow dr3 in dt.Rows)
//                {
//                    String s = dr3["ConsultHistory"] == DBNull.Value ? "" : filter_b(dr3["ConsultHistory"]);
//                    buffer.AppendFormat(
//
//                        #region 咨询记录
//                        @"	<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1554""/>
//				<w:gridCol w:w=""2210""/>
//				<w:gridCol w:w=""1528""/>
//				<w:gridCol w:w=""3230""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生心理咨询谈话记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1647"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询师</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2340"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1620"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询时间</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3447"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//<w:p wsp:rsidR=""0028502F"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""00915363"">
//	<w:pPr>
//		<w:jc w:val=""left""/>
//	</w:pPr>
//</w:p>",
//
//                        #endregion
//                    filter_b(dr3["teacher_name"]), dr3["start_time"], filter_consult_history(s));
//                }
//            }
//            //学生相关评语及自评
//            if (option.IndexOf("5") >= 0)
//            {
//                //同学给我的
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(FromID) AS 'student_name',Theme,content,CreateDate FROM CommentSS WHERE ToID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生相关评语或自评</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                foreach (DataRow dr6 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 评价
//                        @"<w:p wsp:rsidR=""00E729B8"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E729B8"" wsp:rsidP=""00E729B8"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>同学的评价：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E729B8"" wsp:rsidP=""00E729B8"">
//						<w:pPr>
//							<w:rPr>
//								<w:kern w:val=""0""/>
//								<w:sz w:val=""20""/>
//								<w:sz-cs w:val=""20""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion,
//                        filter_b(dr6["theme"]), filter_b(dr6["student_name"]), dr6["createdate"], filter_b(dr6["content"]));
//                }
//
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""0028502F"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//
//            //综合评价
//            if (option.IndexOf("6") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(FromID) AS 'student_name',Theme,content,CreateDate FROM CommentSS WHERE ToID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00E74C72"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E74C72"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>综合性评价</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00E74C72"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                foreach (DataRow dr6 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 评价
//                        @"<w:p wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E74C72"" wsp:rsidP=""00E74C72"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教师的评价：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E74C72"" wsp:rsidP=""00E74C72"">
//						<w:pPr>
//							<w:rPr>
//								<w:kern w:val=""0""/>
//								<w:sz w:val=""20""/>
//								<w:sz-cs w:val=""20""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion,
//                        filter_b(dr6["theme"]), filter_b(dr6["student_name"]), dr6["createdate"], filter_b(dr6["content"]));
//                }
//
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00E74C72"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//
//            buffer.Append(
//
//                #region doc_tail
//                @"<w:sectPr wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00EE1AAD"" wsp:rsidSect=""009D1BC2"">
//			<w:pgSz w:w=""11906"" w:h=""16838""/>
//			<w:pgMar w:top=""1440"" w:right=""1800"" w:bottom=""1440"" w:left=""1800"" w:header=""851"" w:footer=""992"" w:gutter=""0""/>
//			<w:cols w:space=""425""/>
//			<w:docGrid w:type=""lines"" w:line-pitch=""312""/>
//		</w:sectPr>
//	</w:body>
//</w:wordDocument>"
//                #endregion
//
//                );
//            con.Close();
//            return buffer.toString();
//        }
//
//        /// <summary>
//        /// 获取教师档案
//        /// </summary>
//        /// <param name="uid">用户ID</param>
//        /// <param name="option">档案内容</param>
//        /// <returns>档案内容 word2003 xml</returns>
//        public static String GetTeacherArchive(long uid, String option)
//        {
//            var con = new SqlConnection(Global.DB_CON);
//            con.Open();
//            String sql =
//                String.Format(
//                    @"select *,dbo.GetTeacherType(TeacherType) as FullTeacherType,dbo.GetDepartMent(Department) as FullDepartment 
//									from teacher WHERE ID = {0}",
//                    uid);
//            DataTable dt = ExecuteDataTable(con,sql);
//            if (dt.Rows.Count == 0)
//            {
//                con.Close();
//                return "用户不存在";
//            }
//            DataRow dr = dt.Rows[0];
//            String student_name = filter_b(dr["Name"]);
//            var buffer = new StringBuilder();
//            buffer.Append(
//
//                #region 表头
//                @"<?xml version=""1.0"" encoding=""UTF-8"" standalone=""yes""?>
//<?mso-application progid=""Word.Document""?>
//<w:wordDocument xmlns:aml=""http://schemas.microsoft.com/aml/2001/core"" xmlns:dt=""uuid:C2F41010-65B3-11d1-A29F-00AA00C14882"" xmlns:ve=""http://schemas.openxmlformats.org/markup-compatibility/2006"" xmlns:o=""urn:schemas-microsoft-com:office:office"" xmlns:v=""urn:schemas-microsoft-com:vml"" xmlns:w10=""urn:schemas-microsoft-com:office:word"" xmlns:w=""http://schemas.microsoft.com/office/word/2003/wordml"" xmlns:wx=""http://schemas.microsoft.com/office/word/2003/auxHint"" xmlns:wsp=""http://schemas.microsoft.com/office/word/2003/wordml/sp2"" xmlns:sl=""http://schemas.microsoft.com/schemaLibrary/2003/core"" w:macrosPresent=""no"" w:embeddedObjPresent=""no"" w:ocxPresent=""no"" xml:space=""preserve"">
//	<w:ignoreSubtree w:val=""http://schemas.microsoft.com/office/word/2003/wordml/sp2""/>
//	<o:DocumentProperties>
//		<o:Author>USER</o:Author>
//		<o:LastAuthor>Zhengcx</o:LastAuthor>
//		<o:Revision>38</o:Revision>
//		<o:TotalTime>174</o:TotalTime>
//		<o:Created>{0}</o:Created>
//		<o:LastSaved>{0}</o:LastSaved>
//		<o:Pages>3</o:Pages>
//		<o:Words>57</o:Words>
//		<o:Characters>325</o:Characters>
//		<o:Company>NSA</o:Company>
//		<o:Lines>2</o:Lines>
//		<o:Paragraphs>1</o:Paragraphs>
//		<o:CharactersWithSpaces>381</o:CharactersWithSpaces>
//		<o:Version>12</o:Version>
//	</o:DocumentProperties>
//	<w:fonts>
//		<w:defaultFonts w:ascii=""Calibri"" w:fareast=""宋体"" w:h-ansi=""Calibri"" w:cs=""Times New Roman""/>
//		<w:font w:name=""Times New Roman"">
//			<w:panose-1 w:val=""02020603050405020304""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Roman""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""20002A87"" w:usb-1=""80000000"" w:usb-2=""00000008"" w:usb-3=""00000000"" w:csb-0=""000001FF"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""宋体"">
//			<w:altName w:val=""SimSun""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""黑体"">
//			<w:altName w:val=""SimHei""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Cambria Math"">
//			<w:panose-1 w:val=""02040503050406030204""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Roman""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""A00002EF"" w:usb-1=""420020EB"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""0000009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Calibri"">
//			<w:panose-1 w:val=""020F0502020204030204""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Swiss""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""A00002EF"" w:usb-1=""4000207B"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""0000009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@宋体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@黑体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""楷体_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@楷体_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//	</w:fonts>
//	<w:styles>
//		<w:versionOfBuiltInStylenames w:val=""7""/>
//		<w:latentStyles w:defLockedState=""off"" w:latentStyleCount=""267"">
//			<w:lsdException w:name=""Normal""/>
//			<w:lsdException w:name=""heading 1""/>
//			<w:lsdException w:name=""heading 2""/>
//			<w:lsdException w:name=""heading 3""/>
//			<w:lsdException w:name=""heading 4""/>
//			<w:lsdException w:name=""heading 5""/>
//			<w:lsdException w:name=""heading 6""/>
//			<w:lsdException w:name=""heading 7""/>
//			<w:lsdException w:name=""heading 8""/>
//			<w:lsdException w:name=""heading 9""/>
//			<w:lsdException w:name=""caption""/>
//			<w:lsdException w:name=""Title""/>
//			<w:lsdException w:name=""Subtitle""/>
//			<w:lsdException w:name=""Strong""/>
//			<w:lsdException w:name=""Emphasis""/>
//			<w:lsdException w:name=""No Spacing""/>
//			<w:lsdException w:name=""List Paragraph""/>
//			<w:lsdException w:name=""Quote""/>
//			<w:lsdException w:name=""Intense Quote""/>
//			<w:lsdException w:name=""Subtle Emphasis""/>
//			<w:lsdException w:name=""Intense Emphasis""/>
//			<w:lsdException w:name=""Subtle Reference""/>
//			<w:lsdException w:name=""Intense Reference""/>
//			<w:lsdException w:name=""Book Title""/>
//			<w:lsdException w:name=""TOC Heading""/>
//		</w:latentStyles>
//		<w:style w:type=""paragraph"" w:default=""on"" w:styleId=""a"">
//			<w:name w:val=""Normal""/>
//			<wx:uiName wx:val=""正文""/>
//			<w:rsid w:val=""009D1BC2""/>
//			<w:pPr>
//				<w:widowControl w:val=""off""/>
//				<w:jc w:val=""both""/>
//			</w:pPr>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:kern w:val=""2""/>
//				<w:sz w:val=""21""/>
//				<w:sz-cs w:val=""22""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//		</w:style>
//		<w:style w:type=""character"" w:default=""on"" w:styleId=""a0"">
//			<w:name w:val=""Default Paragraph Font""/>
//			<wx:uiName wx:val=""默认段落字体""/>
//		</w:style>
//		<w:style w:type=""table"" w:default=""on"" w:styleId=""a1"">
//			<w:name w:val=""Normal Table""/>
//			<wx:uiName wx:val=""普通表格""/>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//			<w:tblPr>
//				<w:tblInd w:w=""0"" w:type=""dxa""/>
//				<w:tblCellMar>
//					<w:top w:w=""0"" w:type=""dxa""/>
//					<w:left w:w=""108"" w:type=""dxa""/>
//					<w:bottom w:w=""0"" w:type=""dxa""/>
//					<w:right w:w=""108"" w:type=""dxa""/>
//				</w:tblCellMar>
//			</w:tblPr>
//		</w:style>
//		<w:style w:type=""list"" w:default=""on"" w:styleId=""a2"">
//			<w:name w:val=""No List""/>
//			<wx:uiName wx:val=""无列表""/>
//		</w:style>
//	</w:styles>
//	<w:shapeDefaults>
//		<o:shapedefaults v:ext=""edit"" spidmax=""4098""/>
//		<o:shapelayout v:ext=""edit"">
//			<o:idmap v:ext=""edit"" data=""1""/>
//		</o:shapelayout>
//	</w:shapeDefaults>
//	<w:docPr>
//		<w:view w:val=""print""/>
//		<w:zoom w:percent=""100""/>
//		<w:doNotEmbedSystemFonts/>
//		<w:bordersDontSurroundHeader/>
//		<w:bordersDontSurroundFooter/>
//		<w:proofState w:spelling=""clean"" w:grammar=""clean""/>
//		<w:defaultTabStop w:val=""420""/>
//		<w:drawingGridVerticalSpacing w:val=""156""/>
//		<w:displayHorizontalDrawingGridEvery w:val=""0""/>
//		<w:displayVerticalDrawingGridEvery w:val=""2""/>
//		<w:punctuationKerning/>
//		<w:characterSpacingControl w:val=""CompressPunctuation""/>
//		<w:optimizeForBrowser/>
//		<w:validateAgainstSchema/>
//		<w:saveInvalidXML w:val=""off""/>
//		<w:ignoreMixedContent w:val=""off""/>
//		<w:alwaysShowPlaceholderText w:val=""off""/>
//		<w:compat>
//			<w:spaceForUL/>
//			<w:balanceSingleByteDoubleByteWidth/>
//			<w:doNotLeaveBackslashAlone/>
//			<w:ulTrailSpace/>
//			<w:doNotExpandShiftReturn/>
//			<w:adjustLineHeightInTable/>
//			<w:breakWrappedTables/>
//			<w:snapToGridInCell/>
//			<w:wrapTextWithPunct/>
//			<w:useAsianBreakRules/>
//			<w:dontGrowAutofit/>
//			<w:useFELayout/>
//		</w:compat>
//		<wsp:rsids>
//			<wsp:rsidRoot wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""000D577E""/>
//			<wsp:rsid wsp:val=""001A79FD""/>
//			<wsp:rsid wsp:val=""002327B1""/>
//			<wsp:rsid wsp:val=""0028502F""/>
//			<wsp:rsid wsp:val=""0029439E""/>
//			<wsp:rsid wsp:val=""0036319D""/>
//			<wsp:rsid wsp:val=""003F0D2E""/>
//			<wsp:rsid wsp:val=""00444D08""/>
//			<wsp:rsid wsp:val=""004515B8""/>
//			<wsp:rsid wsp:val=""00505A6F""/>
//			<wsp:rsid wsp:val=""005117FA""/>
//			<wsp:rsid wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""006E1ED5""/>
//			<wsp:rsid wsp:val=""006E6797""/>
//			<wsp:rsid wsp:val=""00721AE2""/>
//			<wsp:rsid wsp:val=""0074124B""/>
//			<wsp:rsid wsp:val=""00770155""/>
//			<wsp:rsid wsp:val=""007C4307""/>
//			<wsp:rsid wsp:val=""007F6200""/>
//			<wsp:rsid wsp:val=""00860492""/>
//			<wsp:rsid wsp:val=""00862721""/>
//			<wsp:rsid wsp:val=""00876FC3""/>
//			<wsp:rsid wsp:val=""008E4BEE""/>
//			<wsp:rsid wsp:val=""00915363""/>
//			<wsp:rsid wsp:val=""009A7475""/>
//			<wsp:rsid wsp:val=""009D1BC2""/>
//			<wsp:rsid wsp:val=""00A067D2""/>
//			<wsp:rsid wsp:val=""00A143AA""/>
//			<wsp:rsid wsp:val=""00A725CD""/>
//			<wsp:rsid wsp:val=""00B968AA""/>
//			<wsp:rsid wsp:val=""00BA1D51""/>
//			<wsp:rsid wsp:val=""00BE6A85""/>
//			<wsp:rsid wsp:val=""00C16355""/>
//			<wsp:rsid wsp:val=""00C635A3""/>
//			<wsp:rsid wsp:val=""00CD37B9""/>
//			<wsp:rsid wsp:val=""00CF3752""/>
//			<wsp:rsid wsp:val=""00DA7BD9""/>
//			<wsp:rsid wsp:val=""00DC28CC""/>
//			<wsp:rsid wsp:val=""00DC534A""/>
//			<wsp:rsid wsp:val=""00E729B8""/>
//			<wsp:rsid wsp:val=""00E74C72""/>
//			<wsp:rsid wsp:val=""00EA19EE""/>
//			<wsp:rsid wsp:val=""00EE1AAD""/>
//			<wsp:rsid wsp:val=""00EF78FF""/>
//			<wsp:rsid wsp:val=""00F02057""/>
//			<wsp:rsid wsp:val=""00F95343""/>
//		</wsp:rsids>
//	</w:docPr>
//	<w:body>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>{1}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""008E4BEE"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>教师</w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>心理档案</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>姓名</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    {2}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>性别</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00505A6F"" wsp:rsidRPr=""00505A6F"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{3}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""007A6A86"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>年龄</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t> </w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>   </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""0074124B"" wsp:rsidRPr=""0074124B"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{4}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""0007707E"" wsp:rsidRDefault=""0029439E"">
//			<w:pPr>
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00F95343"" wsp:rsidRDefault=""00F95343""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00EF78FF"">
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//				<w:t>{6}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1242""/>
//				<w:gridCol w:w=""1567""/>
//				<w:gridCol w:w=""1076""/>
//				<w:gridCol w:w=""1212""/>
//				<w:gridCol w:w=""1107""/>
//				<w:gridCol w:w=""696""/>
//				<w:gridCol w:w=""1622""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00876FC3"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00860492"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1242"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>姓 名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1567"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>性 别</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年 龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""696"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00876FC3"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00876FC3"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{4}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00860492"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1242"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00860492"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>类型</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1567"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""00EA19EE"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{5}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00860492"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>部门</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3015"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""00EA19EE"" wsp:rsidP=""00EA19EE"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{11}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>照</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00860492"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1242"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00860492"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//							</w:rPr>
//							<w:t>职称</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1567"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00EA19EE"" wsp:rsidP=""00EA19EE"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{7}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00860492"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职务</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3015"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00EA19EE"" wsp:rsidP=""00EA19EE"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{8}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00860492"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1242"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00860492"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>出生日期</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""5658"" w:type=""dxa""/>
//						<w:gridSpan w:val=""5""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EA19EE"" wsp:rsidP=""00EA19EE"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{9}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""nil""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""nil""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>片</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CD37B9"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""002A1996"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""6712""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1242"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>简</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:proofErr w:type=""gramStart""/>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>介</w:t>
//						</w:r>
//						<w:proofErr w:type=""gramEnd""/>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7280"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00CD37B9"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CD37B9"" wsp:rsidP=""00213197"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{10}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>				
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//			<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>"
//                #endregion
//
//                );
//            buffer = buffer.Replace("{0}", DateTime.Now.toString()); //0
//            buffer = buffer.Replace("{1}", filter_b(Global.sysconfig.SchoolName)); //1
//            buffer = buffer.Replace("{2}", filter_b(dr["Name"])); //2
//            buffer = buffer.Replace("{3}", filter_b(dr["Sex"])); //3
//            buffer = buffer.Replace("{4}", filter_b(dr["Age"])); //3
//            buffer = buffer.Replace("{6}", DateTime.Today.toString("yyyy-MM-d")); //6
//            buffer = buffer.Replace("{5}", filter_b(dr["FullTeacherType"])); //5
//            buffer = buffer.Replace("{11}", filter_b(dr["FullDepartment"])); //11
//            buffer = buffer.Replace("{7}", filter_b(dr["Title"])); //7
//            buffer = buffer.Replace("{8}", filter_b(dr["Duty"])); //8
//            buffer = dr["Birthday"].toString() != "" ? buffer.Replace("{9}", Convert.ToDateTime(dr["Birthday"]).toString("yyyy-MM-d")) : buffer.Replace("{9}", "");
//            buffer = buffer.Replace("{10}", filter_b(dr["Description"])); //10
//
//            //测评记录
//            if (option.IndexOf("1") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT * FROM test_table_tested_view WHERE uid={0} ORDER BY rid",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//
//                //枚举
//                foreach (DataRow dr2 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 表头
//                        @"
//			<w:tbl>
//				<w:tblPr>
//					<w:tblW w:w=""0"" w:type=""auto""/>
//					<w:jc w:val=""center""/>
//					<w:tblBorders>
//						<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					</w:tblBorders>
//					<w:tblLook w:val=""01E0""/>
//				</w:tblPr>
//				<w:tblGrid>
//					<w:gridCol w:w=""800""/>
//					<w:gridCol w:w=""666""/>
//					<w:gridCol w:w=""3047""/>
//					<w:gridCol w:w=""1698""/>
//					<w:gridCol w:w=""2311""/>
//				</w:tblGrid><w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//					<w:trPr>
//						<w:cantSplit/>
//						<w:tblHeader/>
//						<w:jc w:val=""center""/>
//					</w:trPr>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""1466"" w:type=""dxa""/>
//							<w:gridSpan w:val=""2""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00BD0977"">
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t>量 表</w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""7056"" w:type=""dxa""/>
//							<w:gridSpan w:val=""3""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00EE1AAD"">
//								<w:rPr>
//									<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t>{0}</w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//				</w:tr>
//				<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//					<w:trPr>
//						<w:cantSplit/>
//						<w:tblHeader/>
//						<w:jc w:val=""center""/>
//					</w:trPr>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""1466"" w:type=""dxa""/>
//							<w:gridSpan w:val=""2""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00BD0977"">
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t>测试时间</w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""3047"" w:type=""dxa""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00EE1AAD"">
//								<w:rPr>
//									<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t>{1}</w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""1698"" w:type=""dxa""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00BD0977"">
//								<w:rPr>
//									<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//									<wx:font wx:val=""宋体""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t>指导教师</w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//					<w:tc>
//						<w:tcPr>
//							<w:tcW w:w=""2311"" w:type=""dxa""/>
//							<w:tcBorders>
//								<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//								<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							</w:tcBorders>
//							<w:vAlign w:val=""center""/>
//						</w:tcPr>
//						<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//							<w:pPr>
//								<w:spacing w:line=""360"" w:line-rule=""auto""/>
//								<w:jc w:val=""center""/>
//								<w:rPr>
//									<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//							</w:pPr>
//							<w:r wsp:rsidRPr=""00EE1AAD"">
//								<w:rPr>
//									<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//									<wx:font wx:val=""楷体_UTF-8""/>
//									<w:sz w:val=""24""/>
//								</w:rPr>
//								<w:t></w:t>
//							</w:r>
//						</w:p>
//					</w:tc>
//				</w:tr>",
//
//                        #endregion
//                        filter_b(dr2["tname"]),
//                        filter_b(dr2["starttime"]));
//
//                    String explain = filter_explain(dr2["explain"].toString());
//                    //因子分及解释
//                    //结果解释                    
//
//                    buffer.AppendFormat(
//
//                        #region 结果解释
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>测</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>试</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>结</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>果</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:ind w:first-line-chars=""216"" w:first-line=""518""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>",
//
//                        #endregion
//                        explain);
//
//                    //教师意见
//                    buffer.AppendFormat(
//
//                        #region doc
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>师</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>评</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>语</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00915363"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>",
//
//                        #endregion
//                        filter_b(dr2["guide"]));
//                }
//            }
//
//            //留言记录
//            if (option.IndexOf("4") >= 0)
//            {
//                sql = String.Format("SELECT ID,title,date_time,content FROM ConsultQuestion WHERE StudentID={0}", uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教师网上问题留言记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                //枚举每个问题
//                foreach (DataRow dr4 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 问题
//                        @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>问题：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion
//                        filter_b(dr4["title"]), filter_b(student_name), filter_b(dr4["date_time"]), filter_b(dr4["content"]));
//                    //解答
//                    sql =
//                        String.Format(
//                            "SELECT title,dbo.GetUserName(TeacherID) AS 'teacher_name',date_time,content FROM ConsultAnswer WHERE question_id={0}",
//                            dr4["ID"]);
//                    DataTable dt4 = ExecuteDataTable(con,sql);
//                    foreach (DataRow dr5 in dt4.Rows)
//                    {
//                        buffer.AppendFormat(
//
//                            #region 解答
//                            @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>解答：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                            #endregion
//                            filter_b(dr5["title"]), filter_b(dr5["teacher_name"]), filter_b(dr5["date_time"]), filter_b(dr5["content"]));
//                    }
//                }
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//
//            //咨询记录
//            if (option.IndexOf("2") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(TeacherID) AS 'teacher_name',start_time,ConsultHistory FROM RecordOrder WHERE StudentID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                foreach (DataRow dr3 in dt.Rows)
//                {
//                    String s = dr3["ConsultHistory"]==DBNull.Value?"":dr3["ConsultHistory"].toString();
//                    buffer.AppendFormat(
//
//                        #region 咨询记录
//                        @"	<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1554""/>
//				<w:gridCol w:w=""2210""/>
//				<w:gridCol w:w=""1528""/>
//				<w:gridCol w:w=""3230""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教师心理咨询谈话记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1647"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询师</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2340"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1620"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询时间</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3447"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//<w:p wsp:rsidR=""0028502F"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""00915363"">
//	<w:pPr>
//		<w:jc w:val=""left""/>
//	</w:pPr>
//</w:p>",
//
//                        #endregion
//                        filter_b(dr3["teacher_name"]), filter_b(dr3["start_time"]), filter_consult_history(s));
//                }
//            }
//
//            //老师相关评语及自评
//            //学生给我的
//            if (option.IndexOf("5") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(FromID) AS 'student_name',Theme,content,CreateDate FROM CommentST WHERE ToID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教师相关评语或自评</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                foreach (DataRow dr6 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 评价
//                        @"<w:p wsp:rsidR=""00E729B8"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E729B8"" wsp:rsidP=""00E729B8"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>同学的评价：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00E729B8"" wsp:rsidP=""00E729B8"">
//						<w:pPr>
//							<w:rPr>
//								<w:kern w:val=""0""/>
//								<w:sz w:val=""20""/>
//								<w:sz-cs w:val=""20""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion,
//                        filter_b(dr6["theme"]), filter_b(dr6["student_name"]), filter_b(dr6["createdate"]), filter_b(dr6["content"]));
//                }
//
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""0028502F"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//
//            buffer.Append(
//
//                #region 表尾
//                @"<w:p wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00EE1AAD"" wsp:rsidRDefault=""00E74C72"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>
//		<w:sectPr wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00EE1AAD"" wsp:rsidSect=""009D1BC2"">
//			<w:pgSz w:w=""11906"" w:h=""16838""/>
//			<w:pgMar w:top=""1440"" w:right=""1800"" w:bottom=""1440"" w:left=""1800"" w:header=""851"" w:footer=""992"" w:gutter=""0""/>
//			<w:cols w:space=""425""/>
//			<w:docGrid w:type=""lines"" w:line-pitch=""312""/>
//		</w:sectPr>
//	</w:body>
//</w:wordDocument>"
//                #endregion
//
//                );
//            con.Close();
//            return buffer.toString();
//        }
//
//        /// <summary>
//        /// 获取家长档案
//        /// </summary>
//        /// <param name="uid">用户ID</param>
//        /// <param name="option">档案内容</param>
//        /// <returns>档案内容 word2003 xml</returns>
//        public static String GetParentArchive(long uid, String option)
//        {
//            var con = new SqlConnection(Global.DB_CON);
//            con.Open();
//            String sql =
//                String.Format(@"SELECT * FROM Parents WHERE ParentID = {0}", uid);
//            DataTable dt = ExecuteDataTable(con,sql);
//            if (dt.Rows.Count == 0)
//            {
//                con.Close();
//                return "用户不存在";
//            }
//            DataRow dr = dt.Rows[0];
//            String student_name = filter_b(dr["Name"]);
//            var buffer = new StringBuilder();
//            buffer.Append(
//
//                #region 表头
//                @"<?xml version=""1.0"" encoding=""UTF-8"" standalone=""yes""?>
//<?mso-application progid=""Word.Document""?>
//<w:wordDocument xmlns:aml=""http://schemas.microsoft.com/aml/2001/core"" xmlns:dt=""uuid:C2F41010-65B3-11d1-A29F-00AA00C14882"" xmlns:ve=""http://schemas.openxmlformats.org/markup-compatibility/2006"" xmlns:o=""urn:schemas-microsoft-com:office:office"" xmlns:v=""urn:schemas-microsoft-com:vml"" xmlns:w10=""urn:schemas-microsoft-com:office:word"" xmlns:w=""http://schemas.microsoft.com/office/word/2003/wordml"" xmlns:wx=""http://schemas.microsoft.com/office/word/2003/auxHint"" xmlns:wsp=""http://schemas.microsoft.com/office/word/2003/wordml/sp2"" xmlns:sl=""http://schemas.microsoft.com/schemaLibrary/2003/core"" w:macrosPresent=""no"" w:embeddedObjPresent=""no"" w:ocxPresent=""no"" xml:space=""preserve"">
//	<w:ignoreSubtree w:val=""http://schemas.microsoft.com/office/word/2003/wordml/sp2""/>
//	<o:DocumentProperties>
//		<o:Author>USER</o:Author>
//		<o:LastAuthor>Zhengcx</o:LastAuthor>
//		<o:Revision>35</o:Revision>
//		<o:TotalTime>80</o:TotalTime>
//		<o:Created>{0}</o:Created>
//		<o:LastSaved>{0}</o:LastSaved>
//		<o:Pages>4</o:Pages>
//		<o:Words>99</o:Words>
//		<o:Characters>570</o:Characters>
//		<o:Company>NSA</o:Company>
//		<o:Lines>4</o:Lines>
//		<o:Paragraphs>1</o:Paragraphs>
//		<o:CharactersWithSpaces>668</o:CharactersWithSpaces>
//		<o:Version>12</o:Version>
//	</o:DocumentProperties>
//	<w:fonts>
//		<w:defaultFonts w:ascii=""Calibri"" w:fareast=""宋体"" w:h-ansi=""Calibri"" w:cs=""Times New Roman""/>
//		<w:font w:name=""Times New Roman"">
//			<w:panose-1 w:val=""02020603050405020304""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Roman""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""20002A87"" w:usb-1=""80000000"" w:usb-2=""00000008"" w:usb-3=""00000000"" w:csb-0=""000001FF"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""宋体"">
//			<w:altName w:val=""SimSun""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""黑体"">
//			<w:altName w:val=""SimHei""/>
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Cambria Math"">
//			<w:panose-1 w:val=""02040503050406030204""/>
//			<w:charset w:val=""01""/>
//			<w:family w:val=""Roman""/>
//			<w:notTrueType/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000000"" w:usb-1=""00000000"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""00000000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""Calibri"">
//			<w:panose-1 w:val=""020F0502020204030204""/>
//			<w:charset w:val=""00""/>
//			<w:family w:val=""Swiss""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""A00002EF"" w:usb-1=""4000207B"" w:usb-2=""00000000"" w:usb-3=""00000000"" w:csb-0=""0000009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@宋体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000003"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040001"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@黑体"">
//			<w:panose-1 w:val=""02010600030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""楷体_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@楷体_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""仿宋_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@仿宋_UTF-8"">
//			<w:panose-1 w:val=""02010609030101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""Modern""/>
//			<w:pitch w:val=""fixed""/>
//			<w:sig w:usb-0=""00000001"" w:usb-1=""080E0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""00040000"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//		<w:font w:name=""@华文中宋"">
//			<w:panose-1 w:val=""02010600040101010101""/>
//			<w:charset w:val=""86""/>
//			<w:family w:val=""auto""/>
//			<w:pitch w:val=""variable""/>
//			<w:sig w:usb-0=""00000287"" w:usb-1=""080F0000"" w:usb-2=""00000010"" w:usb-3=""00000000"" w:csb-0=""0004009F"" w:csb-1=""00000000""/>
//		</w:font>
//	</w:fonts>
//	<w:styles>
//		<w:versionOfBuiltInStylenames w:val=""7""/>
//		<w:latentStyles w:defLockedState=""off"" w:latentStyleCount=""267"">
//			<w:lsdException w:name=""Normal""/>
//			<w:lsdException w:name=""heading 1""/>
//			<w:lsdException w:name=""heading 2""/>
//			<w:lsdException w:name=""heading 3""/>
//			<w:lsdException w:name=""heading 4""/>
//			<w:lsdException w:name=""heading 5""/>
//			<w:lsdException w:name=""heading 6""/>
//			<w:lsdException w:name=""heading 7""/>
//			<w:lsdException w:name=""heading 8""/>
//			<w:lsdException w:name=""heading 9""/>
//			<w:lsdException w:name=""caption""/>
//			<w:lsdException w:name=""Title""/>
//			<w:lsdException w:name=""Subtitle""/>
//			<w:lsdException w:name=""Strong""/>
//			<w:lsdException w:name=""Emphasis""/>
//			<w:lsdException w:name=""No Spacing""/>
//			<w:lsdException w:name=""List Paragraph""/>
//			<w:lsdException w:name=""Quote""/>
//			<w:lsdException w:name=""Intense Quote""/>
//			<w:lsdException w:name=""Subtle Emphasis""/>
//			<w:lsdException w:name=""Intense Emphasis""/>
//			<w:lsdException w:name=""Subtle Reference""/>
//			<w:lsdException w:name=""Intense Reference""/>
//			<w:lsdException w:name=""Book Title""/>
//			<w:lsdException w:name=""TOC Heading""/>
//		</w:latentStyles>
//		<w:style w:type=""paragraph"" w:default=""on"" w:styleId=""a"">
//			<w:name w:val=""Normal""/>
//			<wx:uiName wx:val=""正文""/>
//			<w:rsid w:val=""009D1BC2""/>
//			<w:pPr>
//				<w:widowControl w:val=""off""/>
//				<w:jc w:val=""both""/>
//			</w:pPr>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:kern w:val=""2""/>
//				<w:sz w:val=""21""/>
//				<w:sz-cs w:val=""22""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//		</w:style>
//		<w:style w:type=""character"" w:default=""on"" w:styleId=""a0"">
//			<w:name w:val=""Default Paragraph Font""/>
//			<wx:uiName wx:val=""默认段落字体""/>
//		</w:style>
//		<w:style w:type=""table"" w:default=""on"" w:styleId=""a1"">
//			<w:name w:val=""Normal Table""/>
//			<wx:uiName wx:val=""普通表格""/>
//			<w:rPr>
//				<wx:font wx:val=""Calibri""/>
//				<w:lang w:val=""EN-US"" w:fareast=""ZH-CN"" w:bidi=""AR-SA""/>
//			</w:rPr>
//			<w:tblPr>
//				<w:tblInd w:w=""0"" w:type=""dxa""/>
//				<w:tblCellMar>
//					<w:top w:w=""0"" w:type=""dxa""/>
//					<w:left w:w=""108"" w:type=""dxa""/>
//					<w:bottom w:w=""0"" w:type=""dxa""/>
//					<w:right w:w=""108"" w:type=""dxa""/>
//				</w:tblCellMar>
//			</w:tblPr>
//		</w:style>
//		<w:style w:type=""list"" w:default=""on"" w:styleId=""a2"">
//			<w:name w:val=""No List""/>
//			<wx:uiName wx:val=""无列表""/>
//		</w:style>
//	</w:styles>
//	<w:shapeDefaults>
//		<o:shapedefaults v:ext=""edit"" spidmax=""5122""/>
//		<o:shapelayout v:ext=""edit"">
//			<o:idmap v:ext=""edit"" data=""1""/>
//		</o:shapelayout>
//	</w:shapeDefaults>
//	<w:docPr>
//		<w:view w:val=""print""/>
//		<w:zoom w:percent=""100""/>
//		<w:doNotEmbedSystemFonts/>
//		<w:bordersDontSurroundHeader/>
//		<w:bordersDontSurroundFooter/>
//		<w:defaultTabStop w:val=""420""/>
//		<w:drawingGridVerticalSpacing w:val=""156""/>
//		<w:displayHorizontalDrawingGridEvery w:val=""0""/>
//		<w:displayVerticalDrawingGridEvery w:val=""2""/>
//		<w:punctuationKerning/>
//		<w:characterSpacingControl w:val=""CompressPunctuation""/>
//		<w:optimizeForBrowser/>
//		<w:validateAgainstSchema/>
//		<w:saveInvalidXML w:val=""off""/>
//		<w:ignoreMixedContent w:val=""off""/>
//		<w:alwaysShowPlaceholderText w:val=""off""/>
//		<w:compat>
//			<w:spaceForUL/>
//			<w:balanceSingleByteDoubleByteWidth/>
//			<w:doNotLeaveBackslashAlone/>
//			<w:ulTrailSpace/>
//			<w:doNotExpandShiftReturn/>
//			<w:adjustLineHeightInTable/>
//			<w:breakWrappedTables/>
//			<w:snapToGridInCell/>
//			<w:wrapTextWithPunct/>
//			<w:useAsianBreakRules/>
//			<w:dontGrowAutofit/>
//			<w:useFELayout/>
//		</w:compat>
//		<wsp:rsids>
//			<wsp:rsidRoot wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""00016EF6""/>
//			<wsp:rsid wsp:val=""000839E4""/>
//			<wsp:rsid wsp:val=""000B2193""/>
//			<wsp:rsid wsp:val=""000D577E""/>
//			<wsp:rsid wsp:val=""001A79FD""/>
//			<wsp:rsid wsp:val=""001C677B""/>
//			<wsp:rsid wsp:val=""00257977""/>
//			<wsp:rsid wsp:val=""0028502F""/>
//			<wsp:rsid wsp:val=""002F5AD2""/>
//			<wsp:rsid wsp:val=""0036319D""/>
//			<wsp:rsid wsp:val=""003F0D2E""/>
//			<wsp:rsid wsp:val=""00444D08""/>
//			<wsp:rsid wsp:val=""00505A6F""/>
//			<wsp:rsid wsp:val=""005117FA""/>
//			<wsp:rsid wsp:val=""005B5D57""/>
//			<wsp:rsid wsp:val=""006F6C85""/>
//			<wsp:rsid wsp:val=""0074124B""/>
//			<wsp:rsid wsp:val=""007C4307""/>
//			<wsp:rsid wsp:val=""007F6200""/>
//			<wsp:rsid wsp:val=""0082338B""/>
//			<wsp:rsid wsp:val=""00862721""/>
//			<wsp:rsid wsp:val=""00876FC3""/>
//			<wsp:rsid wsp:val=""00915363""/>
//			<wsp:rsid wsp:val=""009A7475""/>
//			<wsp:rsid wsp:val=""009D1BC2""/>
//			<wsp:rsid wsp:val=""00A067D2""/>
//			<wsp:rsid wsp:val=""00A143AA""/>
//			<wsp:rsid wsp:val=""00A5067D""/>
//			<wsp:rsid wsp:val=""00A725CD""/>
//			<wsp:rsid wsp:val=""00AB75E4""/>
//			<wsp:rsid wsp:val=""00BA1D51""/>
//			<wsp:rsid wsp:val=""00BE6A85""/>
//			<wsp:rsid wsp:val=""00C16355""/>
//			<wsp:rsid wsp:val=""00C635A3""/>
//			<wsp:rsid wsp:val=""00CF3752""/>
//			<wsp:rsid wsp:val=""00DA7BD9""/>
//			<wsp:rsid wsp:val=""00DC28CC""/>
//			<wsp:rsid wsp:val=""00DC534A""/>
//			<wsp:rsid wsp:val=""00E729B8""/>
//			<wsp:rsid wsp:val=""00E74C72""/>
//			<wsp:rsid wsp:val=""00EE1AAD""/>
//			<wsp:rsid wsp:val=""00EF78FF""/>
//			<wsp:rsid wsp:val=""00F02057""/>
//		</wsp:rsids>
//	</w:docPr>
//	<w:body>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>{1}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"" wsp:rsidRDefault=""00A5067D"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>家长</w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""48""/>
//					<w:sz-cs w:val=""48""/>
//				</w:rPr>
//				<w:t>心理档案</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>姓名</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    {2}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>性别</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>    </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""00505A6F"" wsp:rsidRPr=""00505A6F"">
//				<w:rPr>
//					<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<wx:font wx:val=""楷体_UTF-8""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{3}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""007A6A86"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:ind w:first-line-chars=""800"" w:first-line=""2891""/>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00F116CE"">
//				<w:rPr>
//					<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//					<wx:font wx:val=""宋体""/>
//					<w:b/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>年龄</w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:ascii=""华文中宋"" w:fareast=""华文中宋"" w:h-ansi=""华文中宋"" w:hint=""fareast""/>
//					<wx:font wx:val=""华文中宋""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>：</w:t>
//			</w:r>
//			<w:r wsp:rsidRPr=""007A6A86"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t> </w:t>
//			</w:r>
//			<w:r>
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>   </w:t>
//			</w:r>
//			<w:r wsp:rsidR=""0074124B"" wsp:rsidRPr=""0074124B"">
//				<w:rPr>
//					<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//				<w:t>{4}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""001C677B"">
//			<w:pPr>
//				<w:rPr>
//					<w:sz w:val=""36""/>
//					<w:sz-cs w:val=""36""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""0007707E"" wsp:rsidRDefault=""00257977""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC""/>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00EF78FF"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//			<w:r wsp:rsidRPr=""00EF78FF"">
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//				<w:t>{6}</w:t>
//			</w:r>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1143""/>
//				<w:gridCol w:w=""696""/>
//				<w:gridCol w:w=""291""/>
//				<w:gridCol w:w=""679""/>
//				<w:gridCol w:w=""1076""/>
//				<w:gridCol w:w=""1212""/>
//				<w:gridCol w:w=""134""/>
//				<w:gridCol w:w=""973""/>
//				<w:gridCol w:w=""283""/>
//				<w:gridCol w:w=""413""/>
//				<w:gridCol w:w=""1622""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0082338B"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00AB75E4"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2130"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家长姓名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4357"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""00257977"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2035"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:vmerge w:val=""restart""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>照</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>片</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0082338B"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00AB75E4"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2130"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>性别</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4357"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""00257977"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2035"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:vmerge/>
//						<w:tcBorders>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0082338B"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00A46C84"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""720""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2130"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>出生年月日</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4357"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""00257977"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{5}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2035"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:vmerge/>
//						<w:tcBorders>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0082338B"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00B91769"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""720""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""6487"" w:type=""dxa""/>
//						<w:gridSpan w:val=""9""/>
//						<w:tcBorders>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>子女信息</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>"
//                #endregion
//
//                );
//            buffer = buffer.Replace("{0}", DateTime.Now.toString()); //0
//            buffer = buffer.Replace("{1}", Global.sysconfig.SchoolName); //1
//            buffer = buffer.Replace("{2}", filter_b(dr["Name"])); //2
//            buffer = buffer.Replace("{3}", filter_b(dr["Sex"])); //3
//            buffer = buffer.Replace("{4}", filter_b(dr["Age"])); //3
//            buffer = buffer.Replace("{6}", DateTime.Today.toString("yyyy-MM-d")); //6
//            buffer = buffer.Replace("{5}", Convert.ToDateTime(dr["Birthday"]).toString("yyyy-MM-d")); //9
//
//            sql =
//                String.Format(
//                    "SELECT *,dbo.GetClassFullName(ClassID) AS ClassFullName FROM StudentInfo WHERE Code={0}",
//                    dr["StudentCode"]);
//            dt = ExecuteDataTable(con,sql);
//            if (dt.Rows.Count == 0)
//            {
//                con.Close();
//                return "";
//            }
//            dr = dt.Rows[0];
//            buffer.Append(
//
//                #region doc
//                @"
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2035"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:vmerge/>
//						<w:tcBorders>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0082338B"" wsp:rsidRDefault=""0082338B"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00D44B6B"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>姓 名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>性 别</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EF78FF"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年 龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00876FC3"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{4}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BC3500"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//							</w:rPr>
//							<w:t>民</w:t>
//						</w:r>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//							</w:rPr>
//							<w:t> </w:t>
//						</w:r>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//							</w:rPr>
//							<w:t>族</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00876FC3"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{7}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>籍贯</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4637"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00921773"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00444D08"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{8}</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00956F47"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学 号</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00444D08"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{5}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学 校</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4637"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""000D577E"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""007E5062"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>班 级</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""006F6C85"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""000D577E"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{9}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00A27E05"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""622""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>通信地址</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""006F6C85"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""006F6C85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""001A79FD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""仿宋_UTF-8"" w:fareast=""仿宋_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""仿宋_UTF-8""/>
//							</w:rPr>
//							<w:t>{10}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>城镇</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""696"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{11}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2046"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>是否住宿生</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1346"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{12}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1669"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学生干部</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1622"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{13}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>爱好</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{14}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>特长</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{15}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>童</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>成</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>长</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>经</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>历</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""00CF3752"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{16}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>身体健康情况</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{17}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>学业情况</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{18}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>奖</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>惩</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>情</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>况</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{19}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""602""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>自</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>我</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>评</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>价</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00CF3752"" wsp:rsidRDefault=""00CF3752"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""left""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00CF3752"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{20}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>单亲</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{21}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>与父母同住</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{22}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家庭排行</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{23}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>父亲姓名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00C635A3"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00C635A3"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{24}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC534A"" wsp:rsidP=""00DA7BD9"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00DC534A"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{25}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""008C60AE"" wsp:rsidRDefault=""00A725CD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>联系电话</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A725CD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A725CD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{26}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""613""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>文化程度</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""003F0D2E"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""003F0D2E"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{27}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职业</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""005117FA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""005117FA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{28}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRDefault=""00DA7BD9"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职务</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00A143AA"" wsp:rsidRDefault=""00A067D2"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A067D2"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{29}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00A143AA"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""621""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>母亲姓名</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00A143AA"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00A143AA"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{30}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>年龄</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00BE6A85"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BE6A85"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{31}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>联系电话</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{32}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00CF3752"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>文化程度</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1666"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{33}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>职业</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1212"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{34}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1107"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""008559A7"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""008C60AE"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//								<w:sz-cs w:val=""24""/>
//							</w:rPr>
//							<w:t>职务</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2318"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{35}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00915363"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2809"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>直系亲属是否有病史</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1076"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00F02057"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00F02057"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{36}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""4637"" w:type=""dxa""/>
//						<w:gridSpan w:val=""6""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>直</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>系</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>亲</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>属</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>病</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>史</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{37}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00915363"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家庭中人际关系气氛</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{38}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>亲朋好友基本情况</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>及联系方式</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{39}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:trHeight w:val=""601""/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1143"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>是否贫困生</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7379"" w:type=""dxa""/>
//						<w:gridSpan w:val=""10""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00915363"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{40}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00DC28CC"" wsp:rsidRDefault=""00DC28CC"" wsp:rsidP=""00DC28CC"">
//			<w:pPr>
//				<w:jc w:val=""center""/>
//			</w:pPr>
//		</w:p>"
//                #endregion
//
//                );
//            //说明:下面采用替换是不得已而为之
//            buffer = buffer.Replace("{1}", Global.sysconfig.SchoolName); //1
//            buffer = buffer.Replace("{2}", filter_b(dr["UserName"])); //2
//            buffer = buffer.Replace("{3}", filter_b(dr["Sex"])); //3
//            buffer = buffer.Replace("{4}", filter_b(dr["Age"])); //4
//            buffer = buffer.Replace("{5}", filter_b(dr["Code"])); //5
//            buffer = buffer.Replace("{6}", DateTime.Today.toString("yyyy-MM-d")); //6
//            buffer = buffer.Replace("{7}", GetNation(ParseInt(dr["Nation"], 0))); //7
//            buffer = buffer.Replace("{8}", filter_b(dr["Native"])); //8
//            buffer = buffer.Replace("{9}", filter_b(dr["ClassFullName"])); //9
//            buffer = buffer.Replace("{10}", filter_b(dr["Address"])); //10
//            buffer = buffer.Replace("{11}", IsTrue(dr["IsCity"]) ? "是" : "否"); //11
//            buffer = buffer.Replace("{12}", IsTrue(dr["IsLiveInSchool"]) ? "是" : "否"); //12
//            buffer = buffer.Replace("{13}", IsTrue(dr["IsSchoolLeader"]) ? "是" : "否"); //13
//            buffer = buffer.Replace("{14}", filter_b(dr["Hobby"])); //14
//            buffer = buffer.Replace("{15}", filter_b(dr["Speciality"])); //15
//            buffer = buffer.Replace("{16}", filter_b(dr["ComeExperience"])); //16
//            buffer = buffer.Replace("{17}", filter_b(dr["BodyWork"])); //17
//            buffer = buffer.Replace("{18}", filter_b(dr["SchoolWork"])); //18
//            buffer = buffer.Replace("{19}", filter_b(dr["RewardsWork"])); //19
//            buffer = buffer.Replace("{20}", filter_b(dr["SelfComment"])); //20
//            buffer = buffer.Replace("{21}", IsTrue(dr["IsSingleParent"]) ? "是" : "否"); //21
//            buffer = buffer.Replace("{22}", IsTrue(dr["IsLiveWithParents"]) ? "是" : "否"); //22
//            buffer = buffer.Replace("{23}", filter_b(dr["HomeOrder"])); //23
//            buffer = buffer.Replace("{24}", filter_b(dr["FatherName"])); //24
//            buffer = buffer.Replace("{25}", filter_b(dr["FatherAge"])); //25
//            buffer = buffer.Replace("{26}", filter_b(dr["FatherTel"])); //26
//            buffer = buffer.Replace("{27}", filter_b(dr["FatherEdu"])); //27
//            buffer = buffer.Replace("{28}", filter_b(dr["FatherOcc"])); //28
//            buffer = buffer.Replace("{29}", filter_b(dr["FatherDuty"])); //29
//            buffer = buffer.Replace("{30}", filter_b(dr["MotherName"])); //30
//            buffer = buffer.Replace("{31}", filter_b(dr["MotherAge"])); //31
//            buffer = buffer.Replace("{32}", filter_b(dr["MotherTel"])); //32
//            buffer = buffer.Replace("{33}", filter_b(dr["MotherEdu"])); //33
//            buffer = buffer.Replace("{34}", filter_b(dr["MotherOcc"])); //34
//            buffer = buffer.Replace("{35}", filter_b(dr["MotherDuty"])); //35
//            buffer = buffer.Replace("{36}", IsTrue(dr["IsIll"].toString()) ? "是" : "否"); //36
//            buffer = buffer.Replace("{37}", filter_b(dr["IllDatails"])); //37
//            buffer = buffer.Replace("{38}", filter_b(dr["OtherPeople"])); //38
//            buffer = buffer.Replace("{39}", filter_b(dr["Friends"])); //39
//            buffer = buffer.Replace("{40}", IsTrue(dr["IsPoor"]) ? "是" : "否"); //40
//
//            //测评记录
//            if (option.IndexOf("1") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT * FROM test_table_tested_view WHERE uid={0} ORDER BY rid",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//
//                //枚举
//                foreach (DataRow dr2 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 表头
//                        @"
//		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""800""/>
//				<w:gridCol w:w=""666""/>
//				<w:gridCol w:w=""3047""/>
//				<w:gridCol w:w=""1698""/>
//				<w:gridCol w:w=""2311""/>
//			</w:tblGrid><w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1466"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>量 表</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7056"" w:type=""dxa""/>
//						<w:gridSpan w:val=""3""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1466"" w:type=""dxa""/>
//						<w:gridSpan w:val=""2""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>测试时间</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3047"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1698"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>指导教师</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2311"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t></w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>",
//
//                        #endregion
//                        filter_b(dr2["tname"]),
//                        filter_b(dr2["starttime"]));
//
//                    String explain = filter_explain(dr2["explain"].toString());
//                    
//
//                    //因子分及解释
//                    //结果解释
//                    
//                    buffer.AppendFormat(
//
//                        #region 结果解释
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>测</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>试</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>结</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>果</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:ind w:first-line-chars=""216"" w:first-line=""518""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>",
//
//                        #endregion
//                    explain);
//
//                    //教师意见
//                    buffer.AppendFormat(
//
//                        #region doc
//                        @"			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""00BA1D51"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""800"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>教</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>师</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>评</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""宋体"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>语</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""7722"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0028502F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:h-ansi=""宋体"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00915363"" wsp:rsidRDefault=""00915363"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//				<w:rPr>
//					<w:rFonts w:hint=""fareast""/>
//				</w:rPr>
//			</w:pPr>
//		</w:p>",
//
//                        #endregion
//                        filter_b(dr2["guide"]));
//                }
//            }
//            //留言记录
//            if (option.IndexOf("4") >= 0)
//            {
//                sql = String.Format("SELECT ID,title,date_time,content FROM ConsultQuestion WHERE StudentID={0}", uid);
//                dt = ExecuteDataTable(con,sql);
//                buffer.Append(
//
//                    #region 表头
//                    @"		<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""8522""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家长网上问题留言记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:cantSplit/>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>"
//                    #endregion
//
//                    );
//                //枚举每个问题
//                foreach (DataRow dr4 in dt.Rows)
//                {
//                    buffer.AppendFormat(
//
//                        #region 问题
//                        @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>问题：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                        #endregion
//                        filter_b(dr4["title"]), student_name, filter_b(dr4["date_time"]), filter_b(dr4["content"]));
//                    //解答
//                    sql =
//                        String.Format(
//                            "SELECT title,dbo.GetUserName(TeacherID) AS 'teacher_name',date_time,content FROM ConsultAnswer WHERE question_id={0}",
//                            dr4["ID"]);
//                    DataTable dt4 = ExecuteDataTable(con,sql);
//                    foreach (DataRow dr5 in dt4.Rows)
//                    {
//                        buffer.AppendFormat(
//
//                            #region 解答
//                            @"<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>解答：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""黑体"" w:fareast=""黑体"" w:hint=""fareast""/>
//								<wx:font wx:val=""黑体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>：</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>[</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz-cs w:val=""21""/>
//							</w:rPr>
//							<w:t>]</w:t>
//						</w:r>
//					</w:p>
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00EE1AAD"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{3}</w:t>
//						</w:r>
//					</w:p>",
//
//                            #endregion
//                            filter_b(dr5["title"]), filter_b(dr5["teacher_name"]), filter_b(dr5["date_time"]), filter_b(dr5["content"]));
//                    }
//                }
//                buffer.Append(
//
//                    #region 表尾
//                    @"
//					<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//						<w:pPr>
//							<w:jc w:val=""left""/>
//						</w:pPr>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//		<w:p wsp:rsidR=""00EE1AAD"" wsp:rsidRDefault=""00EE1AAD"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>"
//                    #endregion
//
//                    );
//            }
//
//            //咨询记录
//            if (option.IndexOf("2") >= 0)
//            {
//                sql =
//                    String.Format(
//                        "SELECT dbo.GetUserName(TeacherID) AS 'teacher_name',start_time,ConsultHistory FROM RecordOrder WHERE StudentID={0}",
//                        uid);
//                dt = ExecuteDataTable(con,sql);
//                foreach (DataRow dr3 in dt.Rows)
//                {
//                    String s = dr3["ConsultHistory"] == DBNull.Value ? "" : dr3["ConsultHistory"].toString();
//                    buffer.AppendFormat(
//
//                        #region 咨询记录
//                        @"	<w:tbl>
//			<w:tblPr>
//				<w:tblW w:w=""0"" w:type=""auto""/>
//				<w:jc w:val=""center""/>
//				<w:tblBorders>
//					<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideH w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//					<w:insideV w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//				</w:tblBorders>
//				<w:tblLook w:val=""01E0""/>
//			</w:tblPr>
//			<w:tblGrid>
//				<w:gridCol w:w=""1554""/>
//				<w:gridCol w:w=""2210""/>
//				<w:gridCol w:w=""1528""/>
//				<w:gridCol w:w=""3230""/>
//			</w:tblGrid>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>家长心理咨询谈话记录</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1647"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询师</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""2340"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{0}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""1620"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""00BD0977"">
//							<w:rPr>
//								<w:rFonts w:hint=""fareast""/>
//								<wx:font wx:val=""宋体""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>咨询时间</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""3447"" w:type=""dxa""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//						<w:vAlign w:val=""center""/>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:jc w:val=""center""/>
//							<w:rPr>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{1}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//			<w:tr wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidTr=""0028502F"">
//				<w:trPr>
//					<w:tblHeader/>
//					<w:jc w:val=""center""/>
//				</w:trPr>
//				<w:tc>
//					<w:tcPr>
//						<w:tcW w:w=""9054"" w:type=""dxa""/>
//						<w:gridSpan w:val=""4""/>
//						<w:tcBorders>
//							<w:top w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:left w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:bottom w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//							<w:right w:val=""single"" w:sz=""4"" wx:bdrwidth=""10"" w:space=""0"" w:color=""auto""/>
//						</w:tcBorders>
//					</w:tcPr>
//					<w:p wsp:rsidR=""0028502F"" wsp:rsidRPr=""00BD0977"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""0030294F"">
//						<w:pPr>
//							<w:spacing w:line=""360"" w:line-rule=""auto""/>
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//						</w:pPr>
//						<w:r wsp:rsidRPr=""0028502F"">
//							<w:rPr>
//								<w:rFonts w:ascii=""楷体_UTF-8"" w:fareast=""楷体_UTF-8"" w:hint=""fareast""/>
//								<wx:font wx:val=""楷体_UTF-8""/>
//								<w:sz w:val=""24""/>
//							</w:rPr>
//							<w:t>{2}</w:t>
//						</w:r>
//					</w:p>
//				</w:tc>
//			</w:tr>
//		</w:tbl>
//<w:p wsp:rsidR=""0028502F"" wsp:rsidRDefault=""0028502F"" wsp:rsidP=""00915363"">
//	<w:pPr>
//		<w:jc w:val=""left""/>
//	</w:pPr>
//</w:p>",
//
//                        #endregion
// filter_b(dr3["teacher_name"]), filter_b(dr3["start_time"]), filter_consult_history(s));
//                }
//            }
//
//            buffer.Append(
//
//                /*表尾*/ 
//                "<w:p wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00EE1AAD"" wsp:rsidRDefault=""00E74C72"" wsp:rsidP=""00915363"">
//			<w:pPr>
//				<w:jc w:val=""left""/>
//			</w:pPr>
//		</w:p>
//		<w:sectPr wsp:rsidR=""00E74C72"" wsp:rsidRPr=""00EE1AAD"" wsp:rsidSect=""009D1BC2"">
//			<w:pgSz w:w=""11906"" w:h=""16838""/>
//			<w:pgMar w:top=""1440"" w:right=""1800"" w:bottom=""1440"" w:left=""1800"" w:header=""851"" w:footer=""992"" w:gutter=""0""/>
//			<w:cols w:space=""425""/>
//			<w:docGrid w:type=""lines"" w:line-pitch=""312""/>
//		</w:sectPr>
//	</w:body>
//</w:wordDocument>"
//
//                );
//            con.Close();
//            return buffer.toString();
//        }
//        private static String filter_explain(String explain)
//        {
//            explain = explain.Replace("\r\n", "");
//            explain = explain.Replace("<br />", "\r\n");
//            explain = explain.Replace("<span class=score>", "");
//            explain = explain.Replace("</span>", "");
//            explain = explain.Replace("<li>", "");
//            explain = explain.Replace("</li>", "\r\n");
//            explain = explain.Replace("<ul>", "");
//            explain = explain.Replace("</ul>", "");
//            explain = explain.Replace("<", "&lt;");
//            explain = explain.Replace(">", "&gt;");
//            return explain;
//        }
//
//        private  static String filter_b(String s)
//        {
//            s = s.Replace("<", "&lt;");
//            s = s.Replace(">", "&gt;");
//            return s;
//        }
//        private  static String filter_b(object o)
//        {
//            return o == null ? "" : filter_b(o.toString());
//        }
//
//        private static String filter_consult_history(String s)
//        {
//            s = s.Replace("<li class=\"usermsg\">","");
//            s = s.Replace("</li>", "");
//            s = s.Replace("<strong>", "");
//            s = s.Replace("</strong>", "");
//            s = s.Replace("<li class=\"servermsg\">", "");
//            s = s.Replace("<", "&lt;");
//            s = s.Replace(">", "&gt;");
//            return s;
//        }
//
//        public static String GetFileExt(String file_name)
//        {
//            int index = file_name.LastIndexOf('.');
//            return index >= 0 ? file_name.SubString(index).ToLower() : "unknown";
//        }
//
//        /// <summary>
//        /// 检测用户名唯一性
//        /// </summary>
//        /// <param name="user_name">用户名</param>
//        /// <returns>true 唯一 false 不唯一</returns>
//        public static Boolean CheckUserName(String user_name)
//        {
//            String _sql = String.Format("SELECT COUNT(*) FROM AllUsers_v2 WHERE username='{0}'", user_name);
//            return (Convert.ToInt32(ExecuteScalar(_sql)) == 0);
//        }
//
//        /// <summary>
//        /// 生成缩略图
//        /// </summary>
//        /// <param name="originalImagePath">源图路径（物理路径）</param>
//        /// <param name="thumbnailPath">缩略图路径（物理路径）</param>
//        /// <param name="width">缩略图宽度</param>
//        /// <param name="height">缩略图高度</param>
//        /// <param name="mode">生成缩略图的方式</param>    
//        public static void MakeThumbnail(String originalImagePath, String thumbnailPath, int width, int height,
//                                         String mode)
//        {
//            Image originalImage = Image.FromFile(originalImagePath);
//            int towidth = width;
//            int toheight = height;
//
//            int x = 0;
//            int y = 0;
//            int ow = originalImage.Width;
//            int oh = originalImage.Height;
//
//            switch (mode)
//            {
//                case "HW": //指定高宽缩放（可能变形）
//                    break;
//                case "W": //指定宽，高按比例
//                    toheight = originalImage.Height*width/originalImage.Width;
//                    break;
//
//                case "H": //指定高，宽按比例
//                    towidth = originalImage.Width*height/originalImage.Height;
//                    break;
//                case "Cut": //指定高宽裁减（不变形）                
//                    if (originalImage.Width/(double) originalImage.Height > towidth/(double) toheight)
//                    {
//                        oh = originalImage.Height;
//                        ow = originalImage.Height*towidth/toheight;
//                        y = 0;
//                        x = (originalImage.Width - ow)/2;
//                    }
//                    else
//                    {
//                        ow = originalImage.Width;
//                        oh = originalImage.Width*height/towidth;
//                        x = 0;
//                        y = (originalImage.Height - oh)/2;
//                    }
//                    break;
//                default:
//                    break;
//            }
//
//            //新建一个bmp图片
//            Image bitmap = new Bitmap(towidth, toheight);
//
//            //新建一个画板
//            Graphics g = Graphics.FromImage(bitmap);
//
//            //设置高质量插值法
//            g.InterpolationMode = InterpolationMode.High;
//
//            //设置高质量,低速度呈现平滑程度
//            g.SmoothingMode = SmoothingMode.HighQuality;
//
//            //清空画布并以透明背景色填充
//            g.Clear(Color.Transparent);
//
//            //在指定位置并且按指定大小绘制原图片的指定部分
//            g.DrawImage(originalImage, new Rectangle(0, 0, towidth, toheight),
//                        new Rectangle(x, y, ow, oh),
//                        GraphicsUnit.Pixel);
//
//            try
//            {
//                //以jpg格式保存缩略图
//                bitmap.Save(thumbnailPath, ImageFormat.Jpeg);
//            }
//            finally
//            {
//                originalImage.Dispose();
//
//                bitmap.Dispose();
//                g.Dispose();
//            }
//        }
//
//        /// <summary>
//        /// 保存SessionID
//        /// </summary>
//        /// <param name="SessionID"></param>
//        /// <param name="uid"></param>
//        /// <param name="user_host_name"></param>
//        public static int SessionToDb(String SessionID, String uid,String user_host_name)
//        {
//            String sql =
//                String.Format(
//                    "DELETE FROM session WHERE DATEDIFF(second,end_time,getdate())>=0;DELETE FROM session WHERE session_id='{0}';INSERT INTO session(session_id,uid,end_time,user_host_name) VALUES('{0}',{1},DATEADD(second,{2},getdate()),'{3}')",
//                    SessionID, uid, Global.SESSION_TIMEOUT,filterHTML(user_host_name));
//            return ExecuteNonQuery(sql);
//        }
//
//        public static int DelSession(String SessionID)
//        {
//            String sql = String.Format("DELETE FROM session WHERE session_id='{0}'", SessionID);
//            return ExecuteNonQuery(sql);
//        }
//
//        /// <summary>
//        /// 弹出对话框
//        /// </summary>
//        /// <param name="page">页</param>
//        /// <param name="title">标题</param>
//        /// <param name="msg">消息内容,支持HTML</param>
//        /// <param name="msg_class">消息类型 error warning success</param>
//        public static void MsgBox(Page page,String title, String msg, String msg_class)
//        {
//            Literal li;
//            if (page.Master == null) return;
//            if (page.Master.FindControl("Literal1") != null) li = (Literal)page.Master.FindControl("Literal1");
//            else
//            {
//                 li = (Literal)page.Master.Master.FindControl("Literal1");
//            }
//
//            if (li != null)
//                li.Text = String.Format(@"<script type=""text/javascript"">showDialog('{0}','{1}','{2}');</script>",title,msg,msg_class);
//        }
//
//        /// <summary>
//        /// 弹出对话框,自动消失
//        /// </summary>
//        /// <param name="page">页</param>
//        /// <param name="title">标题</param>
//        /// <param name="msg">消息内容，支持HTML</param>
//        /// <param name="msg_class">消息类型</param>
//        /// <param name="hide_time">自动消失时间</param>
//        public static void MsgBox(Page page, String title, String msg, String msg_class,int hide_time)
//        {
//            Literal li;
//            if (page.Master.FindControl("Literal1") != null) li = (Literal)page.Master.FindControl("Literal1");
//            else
//            {
//                li = (Literal)page.Master.Master.FindControl("Literal1");
//            }
//            if (li != null)
//                li.Text = String.Format(@"<script type=""text/javascript"">showDialog('{0}','{1}','{2}',{3});</script>", title, msg, msg_class,hide_time);
//        }
//
//        /// <summary>
//        /// 画出量表因子分数段图
//        /// </summary>
//        /// <param name="tid"></param>
//        /// <returns></returns>
//        public static String draw_gene_value (int tid)
//        {
//            var con = new SqlConnection(Global.DB_CON);
//            con.Open();
//            //画出因子分数分布图
//            StringBuilder table = new StringBuilder();
//            //获取因子列表
//            DataTable dt = ExecuteDataTable(con, "SELECT gid,gname,maxvalue,minvalue FROM test_gene WHERE tid=" + tid);
//            foreach (DataRow dr in dt.Rows)
//            {
//                int gid = Convert.ToInt32(dr["gid"]);
//                decimal maxvalue = Convert.ToDecimal(dr["maxvalue"]);
//                decimal minvalue = Convert.ToDecimal(dr["minvalue"]);
//                if (maxvalue == minvalue)
//                {
//                    continue;
//                }
//                //画表格
//                table.AppendFormat("<table border=0 cellpadding=0 cellspacing=0 width=720><caption>{0}({1}-{2})</caption><tr>", dr["gname"],minvalue,maxvalue);
//                //获取分数段
//                DataTable _dt = ExecuteDataTable(con,
//                                                       String.Format("SELECT vid,startvalue,endvalue,explain FROM test_value WHERE gid={0} ORDER BY startvalue ASC", gid));
//                decimal b = minvalue;
//                if (_dt.Rows.Count > 0)
//                {
//                    foreach (DataRow _dr in _dt.Rows)
//                    {
//                        decimal startvalue = Convert.ToDecimal(_dr["startvalue"]);
//                        decimal endvalue = Convert.ToDecimal(_dr["endvalue"]);
//                        if (startvalue > b) //补一个空闲区
//                        {
//                            table.AppendFormat("<td class=bar_free width={0}%></td>\r\n",
//                                               (startvalue - b) * 100 / (maxvalue - minvalue)
//                                );
//                        }
//                        table.AppendFormat("<td class=bar_bussy width={0}% align=center><a href=index7_02_04_01.aspx?vid={3}&tid={4} title='{1}-{2} {5}'>{6}</a></td>\r\n",
//                                (endvalue - startvalue) * 100 / (maxvalue - minvalue), startvalue, endvalue,
//                                _dr["vid"], tid, _dr["explain"], (endvalue - startvalue) * 100 / (maxvalue - minvalue)>8?startvalue+"-"+endvalue:"..."
//                            );
//                        b = endvalue;
//                    }
//                }
//                if (maxvalue > b) //补一个空闲区
//                {
//                    table.AppendFormat("<td class=bar_free width={0}%><font color=#00ff00>.</font></td>\r\n",
//                                       (maxvalue - b) * 100 / (maxvalue - minvalue)
//                        );
//                }
//                table.Append("</tr></table><br />");
//            }
//            con.Close();
//            return table.toString();
//        }
//
//        /// <summary>
//        /// 计算MD5
//        /// </summary>
//        /// <param name="str">要计算的字符串</param>
//        /// <returns>MD5</returns>
//        public static String Md5(String str)
//        {
//            String cl = str;
//            String pwd = "";
//            MD5 md5 = MD5.Create();
//            byte[] s = md5.ComputeHash(Encoding.UTF8.GetBytes(cl));
//            for (int i = 0; i < s.Length; i++)
//            {
//                pwd = pwd + s[i].toString("X2");
//            }
//            return pwd;
//        }
//
//        /// <summary>
//        /// 格式化时间
//        /// </summary>
//        /// <param name="t"></param>
//        /// <returns></returns>
//        public static String FormatTime(int t)
//        {
//            String tt = "";
//            if (t / (60 * 60) > 0)
//            {
//                tt += t / (60 * 60) + "小时";
//                t = t % (60 * 60);
//            }
//            if (t / 60 > 0)
//            {
//                tt += t / 60 + "分";
//                t = t % 60;
//            }
//            if (t > 0) tt += t + "秒";
//            return tt;
//        }
//
//        public static String get_db_String(object o)
//        {
//            return o == null ? "" : o.toString();
//        }
//
//        public static String GetChecked(CheckBoxList checkList)
//        {
//            String selval = "";
//            foreach (ListItem li in checkList.Items)
//            {
//                if (li.Selected)
//                {
//                    selval += li.Value + ',';
//                }
//            }
//            return selval.TrimEnd(',');
//        }
//
//        public static void SetChecked(CheckBoxList checkList, String selval)
//        {
//            if (String.IsNullOrEmpty(selval)) return;
//            String[] val = selval.TrimEnd(',').Split(',');
//            foreach (ListItem li in checkList.Items)
//            {
//                li.Selected = false;
//                foreach (var s in val)
//                {
//                    if (s == li.Value)
//                        li.Selected = true;
//                }
//            }
//        }
//
//        public static String GetSystemUpdateInfo()
//        {
//            String content = null;
//
//            //try
//            //{
//            //    HttpWebRequest req =
//            //    (HttpWebRequest)
//            //    WebRequest.Create(
//            //        "http://www.xzt-bj.com/update.txt");
//            //    req.Method = "GET";
//            //    req.Accept = "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, */*";
//            //    req.UserAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; User-agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; http://bsalsa.com) )";
//            //    req.Timeout = 10000;
//            //    var response = (HttpWebResponse) req.GetResponse();
//            //    var reader = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding("gb2312"));
//            //    content = reader.ReadToEnd();
//            //    response.Close();
//            //}
//            //catch
//            //{
//            //}
//
//            return (content!= null && !"".equals(content)) ?"暂无更新":content;
//        }
//
//        public static Boolean get_sex(int rid)
//        {
//            Boolean sex;
//            Object o =
//                ExecuteScalar(String.format(
//                                        "SELECT sex FROM AllUsers_v WHERE uid=(SELECT uid FROM test_record WHERE rid=%d)",
//                                        rid));
//            sex = o != DBNull.Value ? (o.toString() == "男" ? true : false) : false;
//
//            return sex;
//        }
    }
