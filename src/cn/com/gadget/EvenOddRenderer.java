package cn.com.gadget;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	private int[][] arry = new int[100][10];

	public EvenOddRenderer() {

	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
		Color foreground, background;

		foreground = Color.BLACK;// ����Ĭ��ǰ��ɫ
		background = Color.WHITE;// ����Ĭ�ϱ���ɫ

		/*
		 * ��� arry[i][j] = 1 ��ô�����Ԫ���ɺ�ɫ
		 */
		for (int i = 0; i < arry.length; i++) {
			for (int j = 0; j < arry[i].length; j++) {
				if (arry[i][j] == 1) {
					if (row == i && column == j + 1) { // ��һ�в���ɫ
						foreground = Color.YELLOW;
						background = Color.PINK;

					}
				}
			}
		}

		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}

	public void setArry(int[][] arry) {
		this.arry = arry;
	}
}
