package com.buchi.fullentry.movie.model

import com.buchi.fullentry.movie.data.network.MovieDto

data class MovieListResponse(
    val id: Int,
    val page: Int,
    val averageRating: Double,
    val results: List<MovieDto>,
    val totalPages: Int,
    val totalResults: Int
)

/*
{
  "average_rating": 6.82273,
  "backdrop_path": "/eS8rJ1KzRNBewx9MduiSHM4kr7S.jpg",
  "comments": {
    "movie:10193": null,
    "movie:121": null,
    "movie:12155": null,
    "movie:122": null,
    "movie:12444": null,
    "movie:12445": null,
    "movie:155": null,
    "movie:1865": null,
    "movie:1893": null,
    "movie:19995": null,
    "movie:24428": null,
    "movie:285": null,
    "movie:38356": null,
    "movie:49026": null,
    "movie:58": null,
    "movie:597": null,
    "movie:671": null,
    "movie:675": null,
    "movie:767": null,
    "movie:8587": null
  },
  "created_by": {
    "gravatar_hash": "c9e9fc152ee756a900db85757c29815d",
    "id": "4bc8892a017a3c0f92000002",
    "name": "Travis Bell",
    "username": "travisbell"
  },
  "description": "Name pretty much says it all, here's the top 50 grossing films of all time.",
  "id": 10,
  "iso_3166_1": "US",
  "iso_639_1": "en",
  "name": "Top 50 Grossing Films of All Time (Worldwide)",
  "object_ids": {
    "movie:10193": "4bc8ac2d017a3c122d04ee96",
    "movie:121": "4bc88bb5017a3c122d002278",
    "movie:12155": "4bc8b4b7017a3c122d05ee30",
    "movie:122": "4bc88bbb017a3c122d002303",
    "movie:12444": "4bc8b55f017a3c122d0604cd",
    "movie:12445": "4bc8b55f017a3c122d0604dd",
    "movie:155": "4bc88c0c017a3c122d002cc1",
    "movie:1865": "4bc89735017a3c122d017be5",
    "movie:1893": "4bc8975c017a3c122d0180e5",
    "movie:19995": "4bc8c0b8017a3c122d07b853",
    "movie:24428": "4bc8c584017a3c122d086e4a",
    "movie:285": "4bc88d5a017a3c122d005325",
    "movie:38356": "4bfcbec8017a3c702b0008e6",
    "movie:49026": "4cd4b9935e73d676d3000102",
    "movie:58": "4bc88b0c017a3c122d000d46",
    "movie:597": "4bc89052017a3c122d00a537",
    "movie:671": "4bc89154017a3c122d00c1fa",
    "movie:675": "4bc8916c017a3c122d00c3f4",
    "movie:767": "4bc8923d017a3c122d00da88",
    "movie:8587": "4bc8a629017a3c122d040561"
  },
  "page": 1,
  "poster_path": "/sRbZeVtRKIWybTOVpCRPZtzW5bd.jpg",
  "public": true,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/AmHOQ7rpHwiaUMRjKXztnauSJb7.jpg",
      "genre_ids": [
        28,
        12,
        14,
        878
      ],
      "id": 19995,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Avatar",
      "overview": "In the 22nd century, a paraplegic Marine is dispatched to the moon Pandora on a unique mission, but becomes torn between following orders and protecting an alien civilization.",
      "popularity": 100.348,
      "poster_path": "/jRXYjXNq0Cs2TcJjLkki24MLp7u.jpg",
      "release_date": "2009-12-10",
      "title": "Avatar",
      "video": false,
      "vote_average": 7.5,
      "vote_count": 23416
    },
    {
      "adult": false,
      "backdrop_path": "/6VmFqApQRyZZzmiGOQq2C92jyvH.jpg",
      "genre_ids": [
        18,
        10749
      ],
      "id": 597,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Titanic",
      "overview": "101-year-old Rose DeWitt Bukater tells the story of her life aboard the Titanic, 84 years later. A young Rose boards the ship with her mother and fianc??. Meanwhile, Jack Dawson and Fabrizio De Rossi win third-class tickets aboard the ship. Rose tells the whole story from Titanic's departure through to its death???on its first and last voyage???on April 15, 1912.",
      "popularity": 70.421,
      "poster_path": "/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg",
      "release_date": "1997-11-18",
      "title": "Titanic",
      "video": false,
      "vote_average": 7.9,
      "vote_count": 19205
    },
    {
      "adult": false,
      "backdrop_path": "/nNmJRkg8wWnRmzQDe2FwKbPIsJV.jpg",
      "genre_ids": [
        878,
        28,
        12
      ],
      "id": 24428,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Avengers",
      "overview": "When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!",
      "popularity": 160.672,
      "poster_path": "/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
      "release_date": "2012-04-25",
      "title": "The Avengers",
      "video": false,
      "vote_average": 7.7,
      "vote_count": 24705
    },
    {
      "adult": false,
      "backdrop_path": "/n5A7brJCjejceZmHyujwUTVgQNC.jpg",
      "genre_ids": [
        14,
        12
      ],
      "id": 12445,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Deathly Hallows: Part 2",
      "overview": "Harry, Ron and Hermione continue their quest to vanquish the evil Voldemort once and for all. Just as things begin to look hopeless for the young wizards, Harry discovers a trio of magical objects that endow him with powers to rival Voldemort's formidable skills.",
      "popularity": 108.727,
      "poster_path": "/da22ZBmrDOXOCDRvr8Gic8ldhv4.jpg",
      "release_date": "2011-07-07",
      "title": "Harry Potter and the Deathly Hallows: Part 2",
      "video": false,
      "vote_average": 8.1,
      "vote_count": 15686
    },
    {
      "adult": false,
      "backdrop_path": "/pm0RiwNpSja8gR0BTWpxo5a9Bbl.jpg",
      "genre_ids": [
        12,
        14,
        28
      ],
      "id": 122,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Lord of the Rings: The Return of the King",
      "overview": "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor from Sauron's forces. Meanwhile, Frodo and Sam take the ring closer to the heart of Mordor, the dark lord's realm.",
      "popularity": 62.094,
      "poster_path": "/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
      "release_date": "2003-12-01",
      "title": "The Lord of the Rings: The Return of the King",
      "video": false,
      "vote_average": 8.5,
      "vote_count": 17659
    },
    {
      "adult": false,
      "backdrop_path": "/af2duhAt9evRkxzkhxjgQ1naOM6.jpg",
      "genre_ids": [
        28,
        878,
        12
      ],
      "id": 38356,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Transformers: Dark of the Moon",
      "overview": "The Autobots continue to work for NEST, now no longer in secret. But after discovering a strange artifact during a mission in Chernobyl, it becomes apparent to Optimus Prime that the United States government has been less than forthright with them.",
      "popularity": 12.692,
      "poster_path": "/yUqm7eke9oL0ZmKJfgIJLs8WNuJ.jpg",
      "release_date": "2011-06-28",
      "title": "Transformers: Dark of the Moon",
      "video": false,
      "vote_average": 6.1,
      "vote_count": 6336
    },
    {
      "adult": false,
      "backdrop_path": "/yzsegNpkRCPadnWmshrJzT3ardR.jpg",
      "genre_ids": [
        28,
        80,
        18,
        53
      ],
      "id": 49026,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Dark Knight Rises",
      "overview": "Following the death of District Attorney Harvey Dent, Batman assumes responsibility for Dent's crimes to protect the late attorney's reputation and is subsequently hunted by the Gotham City Police Department. Eight years later, Batman encounters the mysterious Selina Kyle and the villainous Bane, a new terrorist leader who overwhelms Gotham's finest. The Dark Knight resurfaces to protect a city that has branded him an enemy.",
      "popularity": 53.554,
      "poster_path": "/vzvKcPQ4o7TjWeGIn0aGC9FeVNu.jpg",
      "release_date": "2012-07-16",
      "title": "The Dark Knight Rises",
      "video": false,
      "vote_average": 7.7,
      "vote_count": 17801
    },
    {
      "adult": false,
      "backdrop_path": "/5gPPx16QWx071VAI1M0RAVKJ6tc.jpg",
      "genre_ids": [
        12,
        14,
        28
      ],
      "id": 58,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Pirates of the Caribbean: Dead Man's Chest",
      "overview": "Captain Jack Sparrow works his way out of a blood debt with the ghostly Davy Jones to avoid eternal damnation.",
      "popularity": 81.08,
      "poster_path": "/l3peI54mf6Z9EBSvS3hnRmOBbFT.jpg",
      "release_date": "2006-07-06",
      "title": "Pirates of the Caribbean: Dead Man's Chest",
      "video": false,
      "vote_average": 7.3,
      "vote_count": 12210
    },
    {
      "adult": false,
      "backdrop_path": "/wE5JGzujfvDPMIfFjJyrhXFjZLc.jpg",
      "genre_ids": [
        16,
        10751,
        35
      ],
      "id": 10193,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Toy Story 3",
      "overview": "Woody, Buzz, and the rest of Andy's toys haven't been played with in years. With Andy about to go to college, the gang find themselves accidentally left at a nefarious day care center. The toys must band together to escape and return home to Andy.",
      "popularity": 63.754,
      "poster_path": "/4cpGytCB0eqvRks4FAlJoUJiFPG.jpg",
      "release_date": "2010-06-16",
      "title": "Toy Story 3",
      "video": false,
      "vote_average": 7.8,
      "vote_count": 11424
    },
    {
      "adult": false,
      "backdrop_path": "/uzIGtyS6bbnJzGsPL93WCF1FWm8.jpg",
      "genre_ids": [
        12,
        28,
        14
      ],
      "id": 1865,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Pirates of the Caribbean: On Stranger Tides",
      "overview": "Captain Jack Sparrow crosses paths with a woman from his past, and he's not sure if it's love -- or if she's a ruthless con artist who's using him to find the fabled Fountain of Youth. When she forces him aboard the Queen Anne's Revenge, the ship of the formidable pirate Blackbeard, Jack finds himself on an unexpected adventure in which he doesn't know who to fear more: Blackbeard or the woman from his past.",
      "popularity": 73.33,
      "poster_path": "/6WMmcLzVg1ud5xS4yY5ZtarGIZy.jpg",
      "release_date": "2011-05-14",
      "title": "Pirates of the Caribbean: On Stranger Tides",
      "video": false,
      "vote_average": 6.5,
      "vote_count": 10895
    },
    {
      "adult": false,
      "backdrop_path": "/5fu7fzy4NZTsL1Jap00UBIInAuB.jpg",
      "genre_ids": [
        12,
        28,
        878
      ],
      "id": 1893,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Star Wars: Episode I - The Phantom Menace",
      "overview": "Anakin Skywalker, a young slave strong with the Force, is discovered on Tatooine. Meanwhile, the evil Sith have returned, enacting their plot for revenge against the Jedi.",
      "popularity": 32.858,
      "poster_path": "/6wkfovpn7Eq8dYNKaG5PY3q2oq6.jpg",
      "release_date": "1999-05-19",
      "title": "Star Wars: Episode I - The Phantom Menace",
      "video": false,
      "vote_average": 6.5,
      "vote_count": 11024
    },
    {
      "adult": false,
      "backdrop_path": "/20pkC7yJdCV4J1IMKfsCT9QU7zV.jpg",
      "genre_ids": [
        10751,
        14,
        12
      ],
      "id": 12155,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Alice in Wonderland",
      "overview": "Alice, an unpretentious and individual 19-year-old, is betrothed to a dunce of an English nobleman. At her engagement party, she escapes the crowd to consider whether to go through with the marriage and falls down a hole in the garden after spotting an unusual rabbit. Arriving in a strange and surreal place called 'Underland,' she finds herself in a world that resembles the nightmares she had as a child, filled with talking animals, villainous queens and knights, and frumious bandersnatches. Alice realizes that she is there for a reason ??? to conquer the horrific Jabberwocky and restore the rightful queen to her throne.",
      "popularity": 33.59,
      "poster_path": "/o0kre9wRCZz3jjSjaru7QU0UtFz.jpg",
      "release_date": "2010-03-03",
      "title": "Alice in Wonderland",
      "video": false,
      "vote_average": 6.6,
      "vote_count": 11244
    },
    {
      "adult": false,
      "backdrop_path": "/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg",
      "genre_ids": [
        18,
        28,
        80,
        53
      ],
      "id": 155,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Dark Knight",
      "overview": "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
      "popularity": 60.005,
      "poster_path": "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
      "release_date": "2008-07-16",
      "title": "The Dark Knight",
      "video": false,
      "vote_average": 8.5,
      "vote_count": 25118
    },
    {
      "adult": false,
      "backdrop_path": "/hziiv14OpD73u9gAak4XDDfBKa2.jpg",
      "genre_ids": [
        12,
        14
      ],
      "id": 671,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Philosopher's Stone",
      "overview": "Harry Potter has lived under the stairs at his aunt and uncle's house his whole life. But on his 11th birthday, he learns he's a powerful wizard -- with a place waiting for him at the Hogwarts School of Witchcraft and Wizardry. As he learns to harness his newfound powers with the help of the school's kindly headmaster, Harry uncovers the truth about his parents' deaths -- and about the villain who's to blame.",
      "popularity": 163.792,
      "poster_path": "/wuMc08IPKEatf9rnMNXvIDxqP4W.jpg",
      "release_date": "2001-11-16",
      "title": "Harry Potter and the Philosopher's Stone",
      "video": false,
      "vote_average": 7.9,
      "vote_count": 20277
    },
    {
      "adult": false,
      "backdrop_path": "/5D849yJ7isoK17flsH99yJUAMPI.jpg",
      "genre_ids": [
        12,
        14,
        28
      ],
      "id": 285,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Pirates of the Caribbean: At World's End",
      "overview": "Captain Barbossa, long believed to be dead, has come back to life and is headed to the edge of the Earth with Will Turner and Elizabeth Swann. But nothing is quite as it seems.",
      "popularity": 71.546,
      "poster_path": "/8ORuWcrYPgjwUDyCzr7qsOlCdwn.jpg",
      "release_date": "2007-05-19",
      "title": "Pirates of the Caribbean: At World's End",
      "video": false,
      "vote_average": 7.2,
      "vote_count": 10928
    },
    {
      "adult": false,
      "backdrop_path": "/AqLcLsGGTzAjm3pCCq0CZCQrp6m.jpg",
      "genre_ids": [
        12,
        14
      ],
      "id": 12444,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Deathly Hallows: Part 1",
      "overview": "Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.",
      "popularity": 100.048,
      "poster_path": "/iGoXIpQb7Pot00EEdwpwPajheZ5.jpg",
      "release_date": "2010-10-17",
      "title": "Harry Potter and the Deathly Hallows: Part 1",
      "video": false,
      "vote_average": 7.8,
      "vote_count": 14566
    },
    {
      "adult": false,
      "backdrop_path": "/wXsQvli6tWqja51pYxXNG1LFIGV.jpg",
      "genre_ids": [
        10751,
        16,
        18
      ],
      "id": 8587,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Lion King",
      "overview": "A young lion prince is cast out of his pride by his cruel uncle, who claims he killed his father. While the uncle rules with an iron paw, the prince grows up beyond the Savannah, living by a philosophy: No worries for the rest of your days. But when his past comes to haunt him, the young prince must decide his fate: Will he remain an outcast or face his demons and become what he needs to be?",
      "popularity": 117.783,
      "poster_path": "/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg",
      "release_date": "1994-06-23",
      "title": "The Lion King",
      "video": false,
      "vote_average": 8.3,
      "vote_count": 13989
    },
    {
      "adult": false,
      "backdrop_path": "/pkxPkHOPJjOvzfQOclANEBT8OfK.jpg",
      "genre_ids": [
        12,
        14,
        9648
      ],
      "id": 675,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Order of the Phoenix",
      "overview": "The rebellion begins! Lord Voldemort has returned, but the Ministry of Magic is doing everything it can to keep the wizarding world from knowing the truth ??? including appointing Ministry official Dolores Umbridge as the new Defence Against the Dark Arts professor at Hogwarts. When Umbridge refuses to teach practical defensive magic, Ron and Hermione convince Harry to secretly train a select group of students for the wizarding war that lies ahead. A terrifying showdown between good and evil awaits in this enthralling film version of the fifth novel in J.K. Rowling???s Harry Potter series. Prepare for battle!",
      "popularity": 111.901,
      "poster_path": "/s836PRwHkLjrOJrfW0eo7B4NJOf.jpg",
      "release_date": "2007-06-28",
      "title": "Harry Potter and the Order of the Phoenix",
      "video": false,
      "vote_average": 7.7,
      "vote_count": 14821
    },
    {
      "adult": false,
      "backdrop_path": "/kT8bDEAgEYBKhRJtqM97qTw6uRW.jpg",
      "genre_ids": [
        12,
        14
      ],
      "id": 767,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "Harry Potter and the Half-Blood Prince",
      "overview": "As Lord Voldemort tightens his grip on both the Muggle and wizarding worlds, Hogwarts is no longer a safe haven. Harry suspects perils may even lie within the castle, but Dumbledore is more intent upon preparing him for the final battle fast approaching. Together they work to find the key to unlock Voldemorts defenses and to this end, Dumbledore recruits his old friend and colleague Horace Slughorn, whom he believes holds crucial information. Even as the decisive showdown looms, romance blossoms for Harry, Ron, Hermione and their classmates. Love is in the air, but danger lies ahead and Hogwarts may never be the same again.",
      "popularity": 114.567,
      "poster_path": "/z7uo9zmQdQwU5ZJHFpv2Upl30i1.jpg",
      "release_date": "2009-07-07",
      "title": "Harry Potter and the Half-Blood Prince",
      "video": false,
      "vote_average": 7.7,
      "vote_count": 14595
    },
    {
      "adult": false,
      "backdrop_path": "/kWYfW2Re0rUDE6IHhy4CRuKWeFr.jpg",
      "genre_ids": [
        12,
        14,
        28
      ],
      "id": 121,
      "media_type": "movie",
      "original_language": "en",
      "original_title": "The Lord of the Rings: The Two Towers",
      "overview": "Frodo and Sam are trekking to Mordor to destroy the One Ring of Power while Gimli, Legolas and Aragorn search for the orc-captured Merry and Pippin. All along, nefarious wizard Saruman awaits the Fellowship members at the Orthanc Tower in Isengard.",
      "popularity": 69.474,
      "poster_path": "/5VTN0pR8gcqV3EPUHHfMGnJYN9L.jpg",
      "release_date": "2002-12-18",
      "title": "The Lord of the Rings: The Two Towers",
      "video": false,
      "vote_average": 8.3,
      "vote_count": 16505
    }
  ],
  "revenue": 47641796988,
  "runtime": 6825,
  "sort_by": "original_order.asc",
  "total_pages": 3,
  "total_results": 50
}
 */
