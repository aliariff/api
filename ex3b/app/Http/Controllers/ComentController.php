<?php

namespace App\Http\Controllers;

use App\Coment;
use Illuminate\Http\Request;

class ComentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $users = Coment::all();
        return $users->toArray();
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {


        //Route::resource('coments', 'ComentController');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $com = new Coment;

        $com->name = $request->name;
        $com->message = $request->messsage;
        $com->Save();


    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Coment  $coment
     * @return \Illuminate\Http\Response
     */
    public function show(Coment $coment)
    {
        $comment = Commnt::find($coment->id);
        return $comment->toArray();
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Coment  $coment
     * @return \Illuminate\Http\Response
     */
    public function edit(Coment $coment)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Coment  $coment
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Coment $coment)
    {
        $comment = Commnt::find($cmment->id);
        $comment->delete();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Coment  $coment
     * @return \Illuminate\Http\Response
     */
    public function destroy(Coment $coment)
    {
        $comment = Commnt::find($coment->id);
        $comment->delete();
    }
}
