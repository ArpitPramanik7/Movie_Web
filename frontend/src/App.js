// src/App.js
import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import Layout from "./components/layout/Layout";
import { Routes, Route } from "react-router-dom";
import Home from "./components/home/Home";
import Header from "./components/header/Header";
import Trailer from "./components/trailer/Trailer";
import Reviews from "./components/reviews/Reviews";
import NotFound from "./components/notFound/NotFound";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import PrivateRoute from "./components/auth/PrivateRoute";

function App() {
  const [movies, setMovies] = useState();
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);

  const getMovies = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await api.get("/api/v1/movies", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setMovies(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const getMovieData = async (movieId) => {
    try {
      const token = localStorage.getItem("token");
      const response = await api.get(`/api/v1/movies/${movieId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const singleMovie = response.data;
      setMovie(singleMovie);
      setReviews(singleMovie.reviews);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getMovies();
  }, []);

  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/" element={<PrivateRoute />}>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home movies={movies} />} />
            <Route path="/Trailer/:ytTrailerId" element={<Trailer />} />
            <Route
              path="/Reviews/:movieId"
              element={
                <Reviews
                  getMovieData={getMovieData}
                  movie={movie}
                  reviews={reviews}
                  setReviews={setReviews}
                />
              }
            />
          </Route>
        </Route>

        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;

