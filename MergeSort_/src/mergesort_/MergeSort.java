
package mergesort_;

import java.util.Scanner;


public class MergeSort
{
    
        // konstruktor MergeSort.
	public MergeSort()
	{
		
	}
	public static void main(String[] args)
	{
		// Buat objek MergeSort
		MergeSort mergesort = new MergeSort();
		
		// Untuk memasukan angka array
		System.out.println("Masukkan array numerik. Pisahkan "
                        + "elemen menggunakan spasi tunggal.");
		
		// Initialize array
		double[] array = null;
		
		// mendapatkan input valid
		while (array == null) {
			array = getUserInput();
		}
		
		// Urutkan susunan
		double[] sorted = mergesort.sort(array);
		
		// Print Array 
		System.out.println("Sorted array: " + arrayToString(sorted));
	}
	
	private double[] sort(double[] array)
	{
		// Dapatkan panjang array
		int l = array.length;
		
		// kembalikan array jika panjangnya 1
		if (l == 1) {
			return array;
		}
		
		// Deklarasi array
		double[] LS;
		// Tambahkan 1 ke kiri array jika panjang array ganjil
		if (l % 2 == 1) {
			LS = new double[l/2+1];
		} else {
			LS = new double[l/2];
		}
		
		// Initialize sisi kanan
		double[] RS = new double[l/2];
		
		// Tambahkan elemen ke sisi kiri
		for (int i = 0; i < LS.length; i++) {
			LS[i] = array[i];
		}
		
		// Tambahkan elemen ke sisi kanan
		for (int i = LS.length; i < l; i++) {
			RS[i-LS.length] = array[i];
		}
		
		// Urutkan sisi kiri dan kanan dan gabungkan hasilnya
		return merge(sort(LS), sort(RS));
	}

	
	private double[] merge(double[] LS, double[] RS)
	{
		// Initialize array
		double[] array = new double[LS.length + RS.length];
		
		// Atur indeks untuk melintasi sisi kiri dan kanan
		int lIndex = 0;
		int rIndex = 0;
		
		// Lintasi sisi kiri dan kanan, tambahkan elemen yang lebih kecil ke array
		while (lIndex < LS.length && rIndex < RS.length) {
                    // Tambahkan elemen yang lebih kecil ke array dan tingkatkan indeks yang sesuai
			if (LS[lIndex] <= RS[rIndex]) {
				array[lIndex + rIndex] = LS[lIndex];
				lIndex++;
			} else {
				array[lIndex + rIndex] = RS[rIndex];
				rIndex++;
			}
		}
		
		// Tambahkan elemen yang tersisa di sisi kiri ke array
		while (lIndex < LS.length) {
			array[lIndex + rIndex] = LS[lIndex];
			lIndex++;
		}
		
		// Tambahkan elemen yang tersisa di sisi kanan ke array
		while (rIndex < RS.length) {
			array[lIndex + rIndex] = RS[rIndex];
			rIndex++;
		}
		
		// Kembalikan array yang dihasilkan
		return array;
	}


	private static double[] getUserInput()
	{
		//Buat pemindai untuk membuat array dari input pengguna
		Scanner scanner = new Scanner(System.in);
		
		// mendapatkan user input
		String line = scanner.nextLine();
		
		// tutup scanner
		scanner.close();
		
		try
		{
			// membuat argumen array
			String[] args = line.split("\\s+");
			
			// Dapatkan jumlah argumen
			int l = args.length;
			
			// Lempar pengecualian jika array argumen kosong
			if (l == 0) { throw new Exception(); }
			
			// Initialize array
			double[] array = new double[l];
			
			//Parse dan tambahkan argumen ke array
			for (int i = 0; i < l; i++) {
				array[i] = Double.parseDouble(args[i]);
			}
			
			// Kembalikan array yang dihasilkan
			return array;
		}
		catch (Exception e)
		{
			// Lempar pesan kesalahan jika input tidak valid
			System.out.println("Invalid input!");
			return null;
		}
	}

	
	private static String arrayToString(double[] array)
	{
		// Initialize string
		String str = "";
		
		// Tambahkan setiap elemen ke string
		for (int i = 0; i < array.length; i++) {
			str += format(array[i]);
			if (i != array.length-1) {
				str += ", ";
			}
		}
		
		// Kembalikan representasi string yang dihasilkan dari array
		return str;
	}
	
	
	private static String format(double n)
	{
		// Hapus titik desimal jika habis dibagi 1
		if (n % 1 == 0) {
			//Izinkan angka keluar dari batas atas dan bawah bilangan bulat
			if (n <= Integer.MAX_VALUE && n >= Integer.MIN_VALUE) {
				return String.valueOf((int) n);
			} else {
				return String.valueOf((long) n);
			}
		} else {
			return String.valueOf(n);
		}
	}

}