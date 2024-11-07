package org.edgar;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.edgar.entities.Post;
import org.edgar.entities.Profile;

import java.time.LocalDate;
import java.util.*;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBManager {
    private final MongoCollection<Profile> profiles; // Colección de perfiles
    private Profile myProfile; // Mi perfil

    public MongoDBManager(String uri, String databaseName, String collectionName) {
        MongoClient mongoClient;
        try {
            mongoClient = MongoClients.create(uri);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

            profiles = database.getCollection(collectionName, Profile.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createProfile(String name, String status, int age, List<Post> posts) {
        myProfile = new Profile(name, status, age, posts);
        profiles.insertOne(myProfile);
    }

    public void publishPost(String title, String content, List<String> comments) {
        Post post = new Post(title, content, comments);
        List<Post> posts = myProfile.getPosts();
        posts.add(post);
        profiles.replaceOne(eq(myProfile.getId()), new Profile(myProfile.getName(), myProfile.getStatus(), myProfile.getAge(), posts));
    }

    public void updateStatus(String status) {
        myProfile.setStatus(status);
        profiles.replaceOne(eq(myProfile.getId()), myProfile);
    }

    public void deleteProfile() {
        profiles.deleteOne(eq(myProfile.getId()));
    }

    public void showProfiles() {
        for (Profile profile : profiles.find()) {
            System.out.println(profile);
        }
    }

    public void showPosts(String profileName) {
        Profile currentProfile = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            currentProfile = profile;
        }
        if (currentProfile != null) {
            System.out.println(currentProfile);
        } else {
            System.out.println("El perfil no existe");
        }
    }

    public void likePost(String profileName, String title) {
        Profile currentProfile = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            currentProfile = profile;
        }
        if (currentProfile != null) {
            if (!currentProfile.getPosts().isEmpty()) {
                for (Post post : currentProfile.getPosts()) {
                    if (post.getTitle().equals(title)) {
                        post.setLikes(post.getLikes() + 1);
                    } else {
                        System.out.println("Post no encontrado");
                    }
                }
            } else {
                System.out.println("El perfil no tiene publicaciones");
            }
        } else {
            System.out.println("El perfil no existe");
        }

        assert currentProfile != null;
        profiles.replaceOne(eq(currentProfile.getId()), currentProfile);
    }

    public void commentPost(String profileName, String title, String comment) {
        Profile currentProfile = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            currentProfile = profile;
        }
        if (currentProfile != null) {
            if (!currentProfile.getPosts().isEmpty()) {
                for (Post post : currentProfile.getPosts()) {
                    if (post.getTitle().equals(title)) {
                        post.addComment(comment);
                    } else {
                        System.out.println("Post no encontrado");
                    }
                }
            } else {
                System.out.println("El perfil no tiene publicaciones");
            }
        } else {
            System.out.println("El perfil no existe");
        }
        assert currentProfile != null;
        profiles.replaceOne(eq(currentProfile.getId()), currentProfile);
    }

    public void showProfileStats() {
        Profile currentProfile = null;
        for (Profile profile : profiles.find(eq("name", myProfile.getName()))) {
            currentProfile = profile;
        }
        if (currentProfile != null) {
            System.out.println("Nombre: " + currentProfile.getName());
            if (!(currentProfile.getPosts() == null)) {
                System.out.println("Numero de publicaciones: " + currentProfile.getPosts().size());
                System.out.println("Total de likes recibidos: " + currentProfile.getPosts().stream().mapToInt(Post::getLikes).sum());

                int counter = 0;
                for (Post post : currentProfile.getPosts()) {
                    if (post.getComments() != null) {
                        for (String comment : post.getComments()) {
                            counter++;
                        }
                    }
                }
                System.out.println("Total de comentarios: " + counter);

            } else {
                System.out.println("""
                        El perfil no tiene publicaciones
                        El perfil no tiene likes
                        El perfil no tiene comentarios""");
            }
        }

    }

    public void showAllStats() {
        int totalPublications = 0;
        int totalLikes = 0;
        int totalComments = 0;
        List<Profile> profilesList = new ArrayList<>();
        List<String> adultUsers = new ArrayList<>();

        for (Profile profile : profiles.find()) {
            profilesList.add(profile);
            if (profile.getAge() >= 18) {
                adultUsers.add(profile.getName());
            }

            if (profile.getPosts() != null) {
                totalPublications += profile.getPosts().size();
                totalLikes += profile.getPosts().stream().mapToInt(Post::getLikes).sum();

                for (Post post : profile.getPosts()) {
                    if (post.getComments() != null) {
                        totalComments += post.getComments().size();
                    }
                }
            }
        }

        profilesList.sort((p1, p2) -> Integer.compare(p2.getPosts().size(), p1.getPosts().size()));

        System.out.println("Número total de perfiles: " + profiles.countDocuments());
        System.out.println("Total de publicaciones: " + totalPublications);
        System.out.println("Total de likes: " + totalLikes);
        System.out.println("Total de comentarios: " + totalComments);
        System.out.println("Usuarios mayores de edad: " + adultUsers);

        // Mostramos los tres perfiles con más publicaciones
        System.out.println("Top 3 perfiles con más publicaciones:");
        profilesList.stream().limit(3).forEach(profile -> System.out.println(profile.getName() + ": " + profile.getPosts().size() + " publicaciones"));
    }
}
