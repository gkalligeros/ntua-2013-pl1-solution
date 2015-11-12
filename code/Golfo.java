import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Golfo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int N = 0, t_x_pos = 0, t_y_pos = 0, x_temp, y_temp, c, b;
		node temp;
		Queue<node> qe_i_1 = new LinkedList<node>();
		Queue<node> qe_i_2 = new LinkedList<node>();
		Queue<node> n_front = new LinkedList<node>();
		char[][] map = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String line = in.readLine();
			String[] a = line.split(" ");

			N = Integer.parseInt(a[0]);
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				line = in.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);

					
					if (map[i][j] == 'T') {

						t_x_pos = i;
						t_y_pos = j;
					}
				}

			}
			in.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		// init 0 and 1 distance
		int i = t_x_pos;
		int j = t_y_pos;

		while (map[i][j] != 'X') {

			if (map[i][j] == 'G') {
				System.out.print(0);
				System.exit(0);
			}
			map[i][j] = '0';
			qe_i_2.add(new node(i, j, 0));

			{
				b = i;
				c = j - 1;
				while ((map[b][c] != 'X') && (map[b][c] != '0')) {
					if (map[b][c] == 'G') {
						System.out.print(0);
						System.exit(0);
					}
					qe_i_1.add(new node(b, c, 1));
					b++;

				}
				b = i;
				c = j + 1;
				while ((map[b][c] != 'X') && (map[b][c] != '0')) {
					if (map[b][c] == 'G') {
						System.out.print(0);
						System.exit(0);
					}
					qe_i_1.add(new node(b, c, 1));
					b++;

				}
			}
			i++;

		}

		/*
		 * for (int i1 = 0; i1 < N; i1++) { for (int j1 = 0; j1 < N; j1++) {
		 * 
		 * System.out.print(map[i1][j1]); } System.out.println(); }
		 */
		// main loop
		//
		int distance = 2;
		while (true) {
			temp = null;
			while (!(qe_i_2.isEmpty())) {
				temp = qe_i_2.remove();
				x_temp = temp.getX();
				y_temp = temp.getY();
				if ((map[x_temp - 1][y_temp] != 'X')
						&& ((map[x_temp - 1][y_temp] != '0'))) {
					n_front.add(new node(x_temp - 1, y_temp, distance));
					if (map[x_temp - 1][y_temp] == 'G') {
						// System.out.print("up");
						// System.out.println(temp.getDst());
						System.out.print(distance);
						System.exit(0);
					} else {
						map[x_temp - 1][y_temp] = '0';

					}
				}

			}
			while (!(qe_i_1.isEmpty())) {
				temp = qe_i_1.remove();
				qe_i_2.add(temp);
				x_temp = temp.getX();
				y_temp = temp.getY();
				i = x_temp;
				j = y_temp - 1;
				if ((map[i][j] != 'X') && (map[i][j] != '0')) {

					while ((map[i][j] != '0') && map[i][j] != 'X') {
						if (map[i][j] == 'G') {
							// System.out.print("left");
							// System.out.println(temp.getDst());

							System.out.print(distance);
							System.exit(0);
						}
						n_front.add(new node(i, j, distance));
						map[i][j] = '0';

						i++;

					}

				}
				i = x_temp;
				j = y_temp + 1;
				if ((map[i][j] != 'X') && (map[i][j] != '0')) {
					while ((map[i][j] != '0') && map[i][j] != 'X') {
						if (map[i][j] == 'G') {
							// System.out.print("right");
							// System.out.println(temp.getDst());

							System.out.print(distance);
							System.exit(0);
						}
						n_front.add(new node(i, j, distance));
						map[i][j] = '0';

						i++;

					}
				}
			}
			if ((n_front.isEmpty()) && (qe_i_2.isEmpty())) {
				System.out.print(-1);
				System.exit(0);
			} else {
				qe_i_1 = new LinkedList<node>(n_front);
				n_front.clear();

			}
			// System.out.println(distance);
			distance++;
		}
	}
}
