import java.util.*;

public class RecentlyPlayedSongsStore {
    private final int initialCapacity;
    private final int maxSongsPerUser;
    private final Map<String, LinkedList<String>> songStore;

    public RecentlyPlayedSongsStore(int initialCapacity, int maxSongsPerUser) {
        this.initialCapacity = initialCapacity;
        this.maxSongsPerUser = maxSongsPerUser;
        this.songStore = new LinkedHashMap<>(initialCapacity, 0.75f, true);
    }

    public void addSong(String user, String song) {
        songStore.putIfAbsent(user, new LinkedList<>());
        LinkedList<String> userSongs = songStore.get(user);

        if (userSongs.size() >= maxSongsPerUser) {
            userSongs.poll(); // Remove the oldest song
        }

        userSongs.add(song);
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        return songStore.getOrDefault(user, new LinkedList<>());
    }

    public static void main(String[] args) {
        RecentlyPlayedSongsStore store = new RecentlyPlayedSongsStore(3, 3);

        store.addSong("User1", "Song1");
        store.addSong("User1", "Song2");
        store.addSong("User1", "Song3");
        List<String> user1Songs = store.getRecentlyPlayedSongs("User1");
        System.out.println("User1's recently played songs: " + user1Songs);

        store.addSong("User2", "SongA");
        store.addSong("User2", "SongB");
        List<String> user2Songs = store.getRecentlyPlayedSongs("User2");
        System.out.println("User2's recently played songs: " + user2Songs);
    }
}
