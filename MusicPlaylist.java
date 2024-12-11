class MusicPlaylist {
    // Node class to represent each song in the playlist
    static class SongNode {
        String songName;
        SongNode next;
        SongNode prev;

        public SongNode(String songName) {
            this.songName = songName;
            this.next = this;
            this.prev = this;
        }
    }

    private SongNode currentSong;

    // Constructor for an empty playlist
    public MusicPlaylist() {
        this.currentSong = null;
    }

    // Add a song to the playlist
    public void addSong(String songName) {
        SongNode newSong = new SongNode(songName);
        
        if (currentSong == null) {
            currentSong = newSong;
        } else {
            SongNode tail = currentSong.prev;
            tail.next = newSong;
            newSong.prev = tail;
            newSong.next = currentSong;
            currentSong.prev = newSong;
        }

        System.out.println("Song '" + songName + "' added to the playlist.");
    }

    // Remove a song from the playlist
    public void removeSong(String songName) {
        if (currentSong == null) {
            System.out.println("The playlist is empty.");
            return;
        }

        SongNode temp = currentSong;
        do {
            if (temp.songName.equals(songName)) {
                if (temp == currentSong && temp.next == currentSong) {
                    currentSong = null; // The playlist becomes empty
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    if (temp == currentSong) {
                        currentSong = temp.next; // Move to the next song if current is removed
                    }
                }
                System.out.println("Song '" + songName + "' removed from the playlist.");
                return;
            }
            temp = temp.next;
        } while (temp != currentSong);

        System.out.println("Song '" + songName + "' not found in the playlist.");
    }

    // Move to the next song in the playlist
    public void nextSong() {
        if (currentSong == null) {
            System.out.println("The playlist is empty.");
            return;
        }
        currentSong = currentSong.next;
        System.out.println("Now playing: " + currentSong.songName);
    }

    // Move to the previous song in the playlist
    public void prevSong() {
        if (currentSong == null) {
            System.out.println("The playlist is empty.");
            return;
        }
        currentSong = currentSong.prev;
        System.out.println("Now playing: " + currentSong.songName);
    }

    // Display the current song
    public void displayCurrentSong() {
        if (currentSong == null) {
            System.out.println("The playlist is empty.");
        } else {
            System.out.println("Currently playing: " + currentSong.songName);
        }
    }

    // Display all songs in the playlist
    public void displayPlaylist() {
        if (currentSong == null) {
            System.out.println("The playlist is empty.");
            return;
        }

        SongNode temp = currentSong;
        System.out.println("Playlist:");
        do {
            System.out.println(temp.songName);
            temp = temp.next;
        } while (temp != currentSong);
    }

    public static void main(String[] args) {
        MusicPlaylist playlist = new MusicPlaylist();

        // Adding songs to the playlist
        playlist.addSong("Abandoned By Benjamin William Hastings & Brandon Lake");
        playlist.addSong("Garments By Housefires");
        playlist.addSong("You Saved Me By Upperroom");
        playlist.addSong("I'll Be Ready By Tiffany Hudson");

        // Displaying all songs in the playlist
        playlist.displayPlaylist();

        // Displaying the current song
        playlist.displayCurrentSong();

        // Moving to the next and previous songs
        playlist.nextSong();
        playlist.nextSong();
        playlist.prevSong();

        // Removing a song from the playlist
        playlist.removeSong("You Saved Me By Upperoom");

        // Displaying the updated playlist
        playlist.displayPlaylist();
    }
}
