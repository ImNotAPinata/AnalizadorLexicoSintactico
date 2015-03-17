package T5;
import java_cup.runtime.*;
import javax.swing.*;


public class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

int columna=1;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NOT_ACCEPT,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"45:8,44,40,42,45,43,41,45:18,39,45:4,28,32,45,37,38,28,26,20,27,22,28,21:10" +
",33,36,30,25,29,45:2,6,3,9,15,5,19,23,17,2,18,23,1,16,8,12,7,23,4,13,14,10," +
"11,23:4,45:4,24,45,6,3,9,15,5,19,23,17,2,18,23,1,16,8,12,7,23,4,13,14,10,11" +
",23:4,34,31,35,45:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,178,
"0,1,2,1,3,1:2,4,5,4,1:7,6,7,8,9,10,11,12,13,1:2,14,13,14:16,15,16,17,18,19," +
"20,1:2,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43" +
",44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,14,63,64,65,66,67" +
",68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,14,90,91" +
",92,93,94,95,96,97,98,99,100,101,102,103,104,105,81,106,107,108,109,110,111" +
",112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,13" +
"0,131,132,133,134,135,136,137,138,139,140,141,142")[0];

	private int yy_nxt[][] = unpackFromString(143,46,
"1,2,123,148,159,46,123,163,165,166,123,167,168,50,123,169,170,171,123,172,3" +
",4,5,123,6,7,8,48,51,9,49,10,47,11,12,13,14,15,16,45,17,18,19,20,21,5,-1:47" +
",123,173,123:2,53,123:14,-1,54,-1,123,54,-1:42,4,24,-1:48,52,-1:46,25,-1:59" +
",17,-1:46,18,-1:46,19,-1:46,20,-1:46,21,-1:2,123:13,65,123:5,-1,54,-1,123,5" +
"4,-1:22,123:7,74,123:11,-1,54,-1,123,54,-1:42,28,-1:25,123:19,-1,54,-1,123," +
"54,-1:60,45,-1:7,123:7,22,123:4,57,123:6,-1,54,-1,123,54,-1:53,10,-1:40,26," +
"-1:43,52,-1:3,52,-1:17,123,23,123:17,-1,54,-1,123,54,-1:22,123:4,27,123:14," +
"-1,54,-1,123,54,-1:22,54:19,-1,54,-1,54:2,-1:22,123:11,128,123:7,-1,54,-1,1" +
"23,54,-1:22,123:5,63,123:7,64,123:5,-1,54,-1,123,54,-1:22,123:8,164,123:10," +
"-1,54,-1,123,54,-1:22,123,66,123:17,-1,54,-1,123,54,-1:22,123:3,67,123:15,-" +
"1,54,-1,123,54,-1:22,123:4,69,123:14,-1,54,-1,123,54,-1:22,123:3,70,123:10," +
"71,123:4,-1,54,-1,123,54,-1:22,134,123:18,-1,54,-1,123,54,-1:22,29,123:18,-" +
"1,54,-1,123,54,-1:22,123:11,77,123:7,-1,54,-1,123,54,-1:22,123:4,136,123:6," +
"152,123:7,-1,54,-1,123,54,-1:22,123:7,130,123:11,-1,54,-1,123,54,-1:22,123:" +
"5,30,123:13,-1,54,-1,123,54,-1:22,123:8,131,123:10,-1,54,-1,123,54,-1:22,12" +
"3:10,78,123:8,-1,54,-1,123,54,-1:22,123:5,79,123:13,-1,54,-1,123,54,-1:22,1" +
"23:4,80,123:14,-1,54,-1,123,54,-1:22,123:14,81,123:4,-1,54,-1,123,54,-1:22," +
"123,82,123:17,-1,54,-1,123,54,-1:22,123:11,31,123:7,-1,54,-1,123,54,-1:22,1" +
"23:12,177,123:6,-1,54,-1,123,54,-1:22,123:13,139,123:5,-1,54,-1,123,54,-1:2" +
"2,123:3,138,123:15,-1,54,-1,123,54,-1:22,123:11,32,123:7,-1,54,-1,123,54,-1" +
":22,123:8,88,123:10,-1,54,-1,123,54,-1:22,123:7,89,123:11,-1,54,-1,123,54,-" +
"1:22,123:5,142,123:13,-1,54,-1,123,54,-1:22,123:11,90,123:7,-1,54,-1,123,54" +
",-1:22,123:15,91,123:3,-1,54,-1,123,54,-1:22,123:11,29,123:7,-1,54,-1,123,5" +
"4,-1:22,123:2,98,123:16,-1,54,-1,123,54,-1:22,123,99,123:17,-1,54,-1,123,54" +
",-1:22,123:14,100,123:4,-1,54,-1,123,54,-1:22,123:13,144,123:5,-1,54,-1,123" +
",54,-1:22,123:5,33,123:13,-1,54,-1,123,54,-1:22,123:7,34,123:11,-1,54,-1,12" +
"3,54,-1:22,123:5,63,123:13,-1,54,-1,123,54,-1:22,123:3,101,123:15,-1,54,-1," +
"123,54,-1:22,123:15,102,123:3,-1,54,-1,123,54,-1:22,123:7,103,123:11,-1,54," +
"-1,123,54,-1:22,123:5,35,123:13,-1,54,-1,123,54,-1:22,123:4,106,123:14,-1,5" +
"4,-1,123,54,-1:22,123:4,36,123:14,-1,54,-1,123,54,-1:22,123:6,158,123:12,-1" +
",54,-1,123,54,-1:22,123:5,37,123:13,-1,54,-1,123,54,-1:22,123:5,109,123:13," +
"-1,54,-1,123,54,-1:22,123,111,123:17,-1,54,-1,123,54,-1:22,123:13,112,123:5" +
",-1,54,-1,123,54,-1:22,123:5,38,123:13,-1,54,-1,123,54,-1:22,123:11,39,123:" +
"7,-1,54,-1,123,54,-1:22,123:12,40,123:6,-1,54,-1,123,54,-1:22,123:3,33,123:" +
"15,-1,54,-1,123,54,-1:22,123:3,114,123:15,-1,54,-1,123,54,-1:22,123:12,41,1" +
"23:6,-1,54,-1,123,54,-1:22,123:15,175,123:3,-1,54,-1,123,54,-1:22,123:4,115" +
",123:14,-1,54,-1,123,54,-1:22,123:4,29,123:14,-1,54,-1,123,54,-1:22,42,123:" +
"18,-1,54,-1,123,54,-1:22,123:11,95,123:7,-1,54,-1,123,54,-1:22,123:7,116,12" +
"3:11,-1,54,-1,123,54,-1:22,123:13,118,123:5,-1,54,-1,123,54,-1:22,123:12,11" +
"9,123:6,-1,54,-1,123,54,-1:22,123:3,147,123:15,-1,54,-1,123,54,-1:22,123:5," +
"120,123:13,-1,54,-1,123,54,-1:22,123:17,122,123,-1,54,-1,123,54,-1:22,123:1" +
"2,43,123:6,-1,54,-1,123,54,-1:22,123:4,44,123:14,-1,54,-1,123,54,-1:22,123:" +
"11,76,123:7,-1,54,-1,123,54,-1:22,123:8,73,123:10,-1,54,-1,123,54,-1:22,123" +
":3,68,123:15,-1,54,-1,123,54,-1:22,123:4,129,123:14,-1,54,-1,123,54,-1:22,1" +
"53,123:18,-1,54,-1,123,54,-1:22,123:7,135,123:11,-1,54,-1,123,54,-1:22,123:" +
"8,86,123:10,-1,54,-1,123,54,-1:22,123:5,87,123:13,-1,54,-1,123,54,-1:22,123" +
":4,155,123:14,-1,54,-1,123,54,-1:22,123,83,123:17,-1,54,-1,123,54,-1:22,123" +
":12,140,123:6,-1,54,-1,123,54,-1:22,123:13,92,123:5,-1,54,-1,123,54,-1:22,1" +
"23:3,84,123:15,-1,54,-1,123,54,-1:22,123:8,97,123:10,-1,54,-1,123,54,-1:22," +
"123:7,96,123:11,-1,54,-1,123,54,-1:22,123:5,94,123:13,-1,54,-1,123,54,-1:22" +
",123,104,123:17,-1,54,-1,123,54,-1:22,123:14,157,123:4,-1,54,-1,123,54,-1:2" +
"2,123:7,105,123:11,-1,54,-1,123,54,-1:22,123:4,107,123:14,-1,54,-1,123,54,-" +
"1:22,123:5,110,123:13,-1,54,-1,123,54,-1:22,123:7,117,123:11,-1,54,-1,123,5" +
"4,-1:22,123:5,121,123:13,-1,54,-1,123,54,-1:22,123:11,55,123:7,-1,54,-1,123" +
",54,-1:22,123:8,133,123:10,-1,54,-1,123,54,-1:22,123:3,72,123:15,-1,54,-1,1" +
"23,54,-1:22,123:4,75,123:14,-1,54,-1,123,54,-1:22,123:7,137,123:11,-1,54,-1" +
",123,54,-1:22,123:4,156,123:14,-1,54,-1,123,54,-1:22,123,85,123:17,-1,54,-1" +
",123,54,-1:22,123:3,93,123:15,-1,54,-1,123,54,-1:22,123:5,143,123:13,-1,54," +
"-1,123,54,-1:22,123:4,108,123:14,-1,54,-1,123,54,-1:22,123:5,113,123:13,-1," +
"54,-1,123,54,-1:22,123:4,56,123:14,-1,54,-1,123,54,-1:22,123:8,132,123:10,-" +
"1,54,-1,123,54,-1:22,123:3,176,123:15,-1,54,-1,123,54,-1:22,123:3,141,123:1" +
"5,-1,54,-1,123,54,-1:22,123:3,58,123,59,123:5,126,123:7,-1,54,-1,123,54,-1:" +
"22,123:3,154,123:15,-1,54,-1,123,54,-1:22,123:9,60,123:9,-1,54,-1,123,54,-1" +
":22,123:5,61,123:13,-1,54,-1,123,54,-1:22,123:4,150,123:14,-1,54,-1,123,54," +
"-1:22,123:6,125,123:12,-1,54,-1,123,54,-1:22,123:4,149,123:14,-1,54,-1,123," +
"54,-1:22,123,127,123:7,151,123:9,-1,54,-1,123,54,-1:22,123:5,160,123:13,-1," +
"54,-1,123,54,-1:22,124,123:4,62,123:13,-1,54,-1,123,54,-1:22,123:2,161,123:" +
"16,-1,54,-1,123,54,-1:22,123:3,145,123:15,-1,54,-1,123,54,-1:22,123:4,146,1" +
"23:14,-1,54,-1,123,54,-1:22,123:4,162,123:14,-1,54,-1,123,54,-1:22,123:13,1" +
"74,123:5,-1,54,-1,123,54,-1:21");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

{return new Symbol(sym.EOF, null); }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{ /* ignore white space. */ }
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{return new Symbol(sym.VARIABLE);}
					case -4:
						break;
					case 3:
						{return new Symbol(sym.COMA);}
					case -5:
						break;
					case 4:
						{return new Symbol(sym.ENTERO);}
					case -6:
						break;
					case 5:
						{JOptionPane.showMessageDialog(null,"ERROR: Caracter ilegal");}
					case -7:
						break;
					case 6:
						{return new Symbol(sym.GUIONBAJO);}
					case -8:
						break;
					case 7:
						{return new Symbol(sym.ASIGNADOR);}
					case -9:
						break;
					case 8:
						{return new Symbol(sym.OPERADORES);}
					case -10:
						break;
					case 9:
						{return new Symbol(sym.COMPARADORES);}
					case -11:
						break;
					case 10:
						{return new Symbol(sym.CONDICIONAL);}
					case -12:
						break;
					case 11:
						{return new Symbol(sym.DOSPUNTOS);}
					case -13:
						break;
					case 12:
						{return new Symbol(sym.LIZQUIERDO);}
					case -14:
						break;
					case 13:
						{return new Symbol(sym.LDERECHO);}
					case -15:
						break;
					case 14:
						{return new Symbol(sym.PUNTOCOMA);}
					case -16:
						break;
					case 15:
						{return new Symbol(sym.PIZQUIERDO);}
					case -17:
						break;
					case 16:
						{return new Symbol(sym.PDERECHO);}
					case -18:
						break;
					case 17:
						{ /* ignore white space. */ }
					case -19:
						break;
					case 18:
						{ /* ignore white space. */ }
					case -20:
						break;
					case 19:
						{ /* ignore white space. */ }
					case -21:
						break;
					case 20:
						{ /* ignore white space. */ }
					case -22:
						break;
					case 21:
						{ /* ignore white space. */ }
					case -23:
						break;
					case 22:
						{return new Symbol(sym.EN);}
					case -24:
						break;
					case 23:
						{return new Symbol(sym.SI);}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.INCREMENTADOR);}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.DECREMENTADOR);}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.LEE);}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.REAL);}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.TIPONUMBER);}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.PARA);}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.SINO);}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.NUEVO);}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.TIPOCHARS);}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.OPCION);}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.RETORNA);}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.ESCRIBE);}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.PORCADA);}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.LIBRERIA);}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.TIPOBOOLEANO);}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.ENTONCES);}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.MIENTRAS);}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.PRINCIPAL);}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.HACERMIENTRAS);}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.MENSAJE);}
					case -45:
						break;
					case 45:
						{ /* ignore white space. */ }
					case -46:
						break;
					case 46:
						{return new Symbol(sym.VARIABLE);}
					case -47:
						break;
					case 47:
						{JOptionPane.showMessageDialog(null,"ERROR: Caracter ilegal");}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.OPERADORES);}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.COMPARADORES);}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.VARIABLE);}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.OPERADORES);}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.COMPARADORES);}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.VARIABLE);}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.VARIABLE);}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.VARIABLE);}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.VARIABLE);}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.VARIABLE);}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.VARIABLE);}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.VARIABLE);}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.VARIABLE);}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.VARIABLE);}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.VARIABLE);}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.VARIABLE);}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.VARIABLE);}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.VARIABLE);}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.VARIABLE);}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.VARIABLE);}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.VARIABLE);}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.VARIABLE);}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.VARIABLE);}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.VARIABLE);}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.VARIABLE);}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.VARIABLE);}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.VARIABLE);}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.VARIABLE);}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.VARIABLE);}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.VARIABLE);}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.VARIABLE);}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.VARIABLE);}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.VARIABLE);}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.VARIABLE);}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.VARIABLE);}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.VARIABLE);}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.VARIABLE);}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.VARIABLE);}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.VARIABLE);}
					case -87:
						break;
					case 87:
						{return new Symbol(sym.VARIABLE);}
					case -88:
						break;
					case 88:
						{return new Symbol(sym.VARIABLE);}
					case -89:
						break;
					case 89:
						{return new Symbol(sym.VARIABLE);}
					case -90:
						break;
					case 90:
						{return new Symbol(sym.VARIABLE);}
					case -91:
						break;
					case 91:
						{return new Symbol(sym.VARIABLE);}
					case -92:
						break;
					case 92:
						{return new Symbol(sym.VARIABLE);}
					case -93:
						break;
					case 93:
						{return new Symbol(sym.VARIABLE);}
					case -94:
						break;
					case 94:
						{return new Symbol(sym.VARIABLE);}
					case -95:
						break;
					case 95:
						{return new Symbol(sym.VARIABLE);}
					case -96:
						break;
					case 96:
						{return new Symbol(sym.VARIABLE);}
					case -97:
						break;
					case 97:
						{return new Symbol(sym.VARIABLE);}
					case -98:
						break;
					case 98:
						{return new Symbol(sym.VARIABLE);}
					case -99:
						break;
					case 99:
						{return new Symbol(sym.VARIABLE);}
					case -100:
						break;
					case 100:
						{return new Symbol(sym.VARIABLE);}
					case -101:
						break;
					case 101:
						{return new Symbol(sym.VARIABLE);}
					case -102:
						break;
					case 102:
						{return new Symbol(sym.VARIABLE);}
					case -103:
						break;
					case 103:
						{return new Symbol(sym.VARIABLE);}
					case -104:
						break;
					case 104:
						{return new Symbol(sym.VARIABLE);}
					case -105:
						break;
					case 105:
						{return new Symbol(sym.VARIABLE);}
					case -106:
						break;
					case 106:
						{return new Symbol(sym.VARIABLE);}
					case -107:
						break;
					case 107:
						{return new Symbol(sym.VARIABLE);}
					case -108:
						break;
					case 108:
						{return new Symbol(sym.VARIABLE);}
					case -109:
						break;
					case 109:
						{return new Symbol(sym.VARIABLE);}
					case -110:
						break;
					case 110:
						{return new Symbol(sym.VARIABLE);}
					case -111:
						break;
					case 111:
						{return new Symbol(sym.VARIABLE);}
					case -112:
						break;
					case 112:
						{return new Symbol(sym.VARIABLE);}
					case -113:
						break;
					case 113:
						{return new Symbol(sym.VARIABLE);}
					case -114:
						break;
					case 114:
						{return new Symbol(sym.VARIABLE);}
					case -115:
						break;
					case 115:
						{return new Symbol(sym.VARIABLE);}
					case -116:
						break;
					case 116:
						{return new Symbol(sym.VARIABLE);}
					case -117:
						break;
					case 117:
						{return new Symbol(sym.VARIABLE);}
					case -118:
						break;
					case 118:
						{return new Symbol(sym.VARIABLE);}
					case -119:
						break;
					case 119:
						{return new Symbol(sym.VARIABLE);}
					case -120:
						break;
					case 120:
						{return new Symbol(sym.VARIABLE);}
					case -121:
						break;
					case 121:
						{return new Symbol(sym.VARIABLE);}
					case -122:
						break;
					case 122:
						{return new Symbol(sym.VARIABLE);}
					case -123:
						break;
					case 123:
						{return new Symbol(sym.VARIABLE);}
					case -124:
						break;
					case 124:
						{return new Symbol(sym.VARIABLE);}
					case -125:
						break;
					case 125:
						{return new Symbol(sym.VARIABLE);}
					case -126:
						break;
					case 126:
						{return new Symbol(sym.VARIABLE);}
					case -127:
						break;
					case 127:
						{return new Symbol(sym.VARIABLE);}
					case -128:
						break;
					case 128:
						{return new Symbol(sym.VARIABLE);}
					case -129:
						break;
					case 129:
						{return new Symbol(sym.VARIABLE);}
					case -130:
						break;
					case 130:
						{return new Symbol(sym.VARIABLE);}
					case -131:
						break;
					case 131:
						{return new Symbol(sym.VARIABLE);}
					case -132:
						break;
					case 132:
						{return new Symbol(sym.VARIABLE);}
					case -133:
						break;
					case 133:
						{return new Symbol(sym.VARIABLE);}
					case -134:
						break;
					case 134:
						{return new Symbol(sym.VARIABLE);}
					case -135:
						break;
					case 135:
						{return new Symbol(sym.VARIABLE);}
					case -136:
						break;
					case 136:
						{return new Symbol(sym.VARIABLE);}
					case -137:
						break;
					case 137:
						{return new Symbol(sym.VARIABLE);}
					case -138:
						break;
					case 138:
						{return new Symbol(sym.VARIABLE);}
					case -139:
						break;
					case 139:
						{return new Symbol(sym.VARIABLE);}
					case -140:
						break;
					case 140:
						{return new Symbol(sym.VARIABLE);}
					case -141:
						break;
					case 141:
						{return new Symbol(sym.VARIABLE);}
					case -142:
						break;
					case 142:
						{return new Symbol(sym.VARIABLE);}
					case -143:
						break;
					case 143:
						{return new Symbol(sym.VARIABLE);}
					case -144:
						break;
					case 144:
						{return new Symbol(sym.VARIABLE);}
					case -145:
						break;
					case 145:
						{return new Symbol(sym.VARIABLE);}
					case -146:
						break;
					case 146:
						{return new Symbol(sym.VARIABLE);}
					case -147:
						break;
					case 147:
						{return new Symbol(sym.VARIABLE);}
					case -148:
						break;
					case 148:
						{return new Symbol(sym.VARIABLE);}
					case -149:
						break;
					case 149:
						{return new Symbol(sym.VARIABLE);}
					case -150:
						break;
					case 150:
						{return new Symbol(sym.VARIABLE);}
					case -151:
						break;
					case 151:
						{return new Symbol(sym.VARIABLE);}
					case -152:
						break;
					case 152:
						{return new Symbol(sym.VARIABLE);}
					case -153:
						break;
					case 153:
						{return new Symbol(sym.VARIABLE);}
					case -154:
						break;
					case 154:
						{return new Symbol(sym.VARIABLE);}
					case -155:
						break;
					case 155:
						{return new Symbol(sym.VARIABLE);}
					case -156:
						break;
					case 156:
						{return new Symbol(sym.VARIABLE);}
					case -157:
						break;
					case 157:
						{return new Symbol(sym.VARIABLE);}
					case -158:
						break;
					case 158:
						{return new Symbol(sym.VARIABLE);}
					case -159:
						break;
					case 159:
						{return new Symbol(sym.VARIABLE);}
					case -160:
						break;
					case 160:
						{return new Symbol(sym.VARIABLE);}
					case -161:
						break;
					case 161:
						{return new Symbol(sym.VARIABLE);}
					case -162:
						break;
					case 162:
						{return new Symbol(sym.VARIABLE);}
					case -163:
						break;
					case 163:
						{return new Symbol(sym.VARIABLE);}
					case -164:
						break;
					case 164:
						{return new Symbol(sym.VARIABLE);}
					case -165:
						break;
					case 165:
						{return new Symbol(sym.VARIABLE);}
					case -166:
						break;
					case 166:
						{return new Symbol(sym.VARIABLE);}
					case -167:
						break;
					case 167:
						{return new Symbol(sym.VARIABLE);}
					case -168:
						break;
					case 168:
						{return new Symbol(sym.VARIABLE);}
					case -169:
						break;
					case 169:
						{return new Symbol(sym.VARIABLE);}
					case -170:
						break;
					case 170:
						{return new Symbol(sym.VARIABLE);}
					case -171:
						break;
					case 171:
						{return new Symbol(sym.VARIABLE);}
					case -172:
						break;
					case 172:
						{return new Symbol(sym.VARIABLE);}
					case -173:
						break;
					case 173:
						{return new Symbol(sym.VARIABLE);}
					case -174:
						break;
					case 174:
						{return new Symbol(sym.VARIABLE);}
					case -175:
						break;
					case 175:
						{return new Symbol(sym.VARIABLE);}
					case -176:
						break;
					case 176:
						{return new Symbol(sym.VARIABLE);}
					case -177:
						break;
					case 177:
						{return new Symbol(sym.VARIABLE);}
					case -178:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
