package CTheFuture;

import java.util.*;


public class iTunesMusic {
	
	
	
	private String album = "";
	private String artist = "";
	private String genre = "";
	private String albumArtist = "";
	private String name = "";
	private int   key = -1;
	private String kind = "";
	private String location = "";
	
	public iTunesMusic()
	{
		this.album  = "";
		this.artist = "";
		this.genre = "";
		this.albumArtist = "";
		this.name = "";
		this.key = -1;	
		this.kind = "";
		this.location = "";
	}
	
	public iTunesMusic(String album,String name,String artist,String albumArtist,String genre, String kind, String location)
	{
		this.album  = album;
		this.artist = artist;
		this.genre = genre;
		this.albumArtist = albumArtist;
		this.name = name;
		this.key = -1;	
		this.kind = kind;
		this.location = location;
	}

	
	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbum() {
		return album;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}
	
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	public String getAlbumArtist() {
		return albumArtist;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
	public int getKey() {
		return key;
	}



	
	public static ArrayList<iTunesMusic> getRecords(String dbURL,String acctName, String password)
	{
		ArrayList<iTunesMusic> accounts = null;
		try {   
			Postgres db = new Postgres();
			//db.connect("//localhost:5432/Account", "account", "keleon68");
			db.connect(dbURL, acctName, password);
			if (db.isConnected())
			{
				String sql = "select  * from albums";
				sql = sql + " order by album asc";
				accounts = db.Select(sql, password);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();   
		}

		return accounts;
	}
	
	
	public static ArrayList<iTunesMusic> getRecords(String orderBY)
	{
		ArrayList<iTunesMusic> albums = null;
		try {   
			Postgres db = new Postgres();
			db.connect("//192.168.1.170:5432/itunesmusic", "itunesmusic", "apple");
			//db.connect(dbURL, acctName, password);
			if (db.isConnected())
			{
				String sql = "select  * from albums";
				sql = sql + " order by " + orderBY + " asc";
				albums = db.Select(sql, "apple");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();   
		}

		return albums;
	}
}
