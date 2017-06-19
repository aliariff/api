<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Coment extends Model
{
    //

    protected $fillable = [
        'name', 'author', 'message',
    ];

}
