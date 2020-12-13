package com.quiz.futbol.utils

object Constants {
    const val DEFAULT_IMAGE = "iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAYAAAD0eNT6AABoxUlEQVR42u2dBXhU19b3p+319973ynv7tbSTTDJzJoHgBIfiDoUCxV1LoaWluBOIB3cI7q4J7u4E1+DuwYKF/e11IDRA5MzMOTNH/v/n+T1XSgOsc/Ze6+y9xGSCIEjt+uSLL3L8j4+Pj8Xb25bHy0soa7bY6/H//pOXxdbPyyKM8PYWZntbhKX8P1fyf77BbBG28f+9l//vQ2Yv2wmzxXrO22K94u1tvWX2siaYvYRE/s9fc5Lov9P/R/+Mfg39Wvp36N/l/3wP/Sz6mfSz3/wetllvf8++9Gfgv74u/ZksFiG31Wr1zpQp09/wyCAIgiAoHQUEBPzJbLYJ3t72MtyZtuJONcTLyzaTO931bxwwd8he1mfc8TIt8SbAsF7mf5c4+rvwQGUG//sEc1rwQKM0//taAwMD/4g3AIIgCNKrPqMvd4vFVsLLYm/KHWEQd5BTvS22LeQg336FM4OSxE8OLvKAYDMPDKaIpxk+1iZmH3sxOkngtvsUrw8EQRCkemXK5Pdf7uhLcqf2C//ynUjH55ynBnbwLmJ9zIODXfy6IZqfjvxs9hGKm80B/8GbBkEQBHlEgiD82cfHnpM7pUb8qzWSf72uMlts1+Cw3RYYXOFB1gpu+whu+wZms292ukrBmwlBEATJqU/ojp6OpvmX6HjugI5wB/QSTlhtOQfWF2LOhLdtLAVmlGNAzw6vLwRBECRJ9CXJj/ELcSfSiTuUxTyZ7SYcrFaDAuE6PyVYwAO3DmZfe34kHUIQBEHvRHfK3NFX4Q4/jBLztJhxDyTzlOcUbKJKC4vFWsnb2/vfWAEQBEEGEX0FUpY5OXwvL+tBOEVDQxUY+96UJ1qLmEzF/4AVAkEQpCN9/bVg5g6/Jc/MXyg2vIHjA6lfGdzn/zmX9y1o5u2dORNWDgRBkMb09h6/pJihLybtwbkBxxFPiPhJEZ0YIX8AgiBIpaK7fN6Ktjk/zl3Cs/QfwYEBmasMEugEidOYNyn6J1YcBEGQB0UbMW3IvCY8ViwDg6MC7gkGnr+du9DA39//H1iJEARBbhBtuPxLvz5twG83Yjgl4NGZB+LJgI+tDg1swgqFIAiSUbSx8k229pskPnGKHZwPUCNPKInQYrHXMJvNf8XKhSAIck6f8W5ulWlDfbuxwsEALbUrfiROdfSxlTdhqBEEQVDGosl5PHu/vzi7Ho4E6OOa4ALPF+hN5ahY4RAEQSlEJVZ8g6xJQ3UMPh4X6Hz8MR8atZyfDFRF0yEIggwtGrLD2++Go98+MNypAE2P5C2Jvb39fLETQBBkCFkslr+YLfZ6vHRvAxwBAGIwsJaSXDHSGIIgXcrX1/cLfrc/gN/t38GmD0AqeFtv8VOBvl8KwufYMSAI0rz4hhbgZbFOwJQ9AKT3FuAtiMd+5evrhx0EgiCt6RPqxc+PNmOwoQPgwrRC3tqazyQoSmsK2woEQaoVZfNTlz4+TW0/Nm8A5LwesO3mwUAtVA9AEKQqCYLwv/yLvyPfoC5hswZAyesB23neIKv9558H/B07DwRBHpO3t/e/udMPpklp2JwBcGuewH1KGKTgGzsRBEFu/eLnm08fvgk9wGYMgCex3fWyCN1wIgBBkKISh/LwzYY2HWy8AKirhJCX2f6GIUQQBMkq2lRocxHrlLHZAqDyDoO2n/gp3Z+xc0EQ5MpR/5/5ZtJO3FSwuQKgoRMBnpDrbWtNlTnYySAIkqw3w3lsrXi73ovYTAHQ8omA9ZyXxd6UL+vPsLNBEJT+cT+fX86z+o9j8wRAVxzmV3ilsMNBEPSRfHz8/dG5DwB9w0/1FvE+AlbseBAE0XS+f/Evg8F8SM9LbJAAGKGHgPU5v+IL8/f3/wd2QAgypj7z8rH/wB3/bWyKABiymdB13ra7Gd8LPsV2CEGG+eq3leRZwoewCQIAOPveDhyCIEivors/8Q4QGx4A4OPSwTlWq9UbOyUE6UhU1scb+fTgd3/PsNEBANLhKc8P6ISpgxCkA5nNvvnEEiBsbAAAyf0DhP0Wiz0XdlAI0qCob/+b7H4hCRsaAMAJXlG1AOYLQJCWvvrFZj58bjg2MACA65yhxGHsrBCkYn31Veb/40d307BhAQBkbyJkEaK9vb3/jZ0WgtSlT8wWez1M6wMAKN07gFcL1KQ9B9suBHlYX38tmHlpXyw2JwCAG0sGF3t7Z86EHRiCPCS+CGvxI/972JAAAO7HesfLx1YNOzEEuVHUw5tn507CBgQA8PxpgG1spkyZ/oadGYKUPvL3sRfgkfdZbDwAAPVMGbSd5IFAHuzQEKSMPuOtfHuJtbnYcAAA6psy+ILvUZ1NGC4EQfLJYvH34QtsKzYZAIAGugiup+Rk7NwQ5LLzF+rzyDoBGwsAQENBwL235YIQBDkqPpXrn3yAzwxsJgAADecGTPz884C/Y0eHIIkym63Z+OI5jQ3E2FhtWViJUuVZg8YtWKeuvVnk4JFs3KTpbPai5WzpinUsdu0mtnrjdrZi3Wa2bOV6Nn/JCjZh6mw2aPhY1qP3ANas5Y+sQsWqzD9LdtgTePA0wHrMx8ffHzs7BGUgHjHX5ln+j7FxGI+8+Yuylj/8xIaOGs/WbNrBTl+8xuKv3JKFrbsPsugpM9kvHbuxkqUrMIuPHTYHbjwJsD40W2zfYYeHoFRV/A/8zmwQNgvjQE64QqVqLCxqKNuwbY9szl4Kew+fYKPGT2F1GzRlNiELngdwVwfBEL7ZfYb9HoJ+v+//f7yd70ZsEMYgf8GiLCg4ku3Yd9itTj8tDp86x0aMmSheF+BkACgfBFhX0+Ay7PyQ4cWz/AvyI/8r2Bj0/7VfrUZdNnPeEnb28k1VOP7UoKuHlm1+FvMP8NyAgkOFLqBxEGRkfcLHa7YRm2dgQ9C1469TvwlbuW6Lap1+auyOOy7mC+B6ACjYOOiZl8XeFK4AMpTMZvNf0ctf/1T6tgZbvmaTphz/h+w8cEQ8EfDx9cMzBUrNEhgjCMKf4RkgAzh/+9f8pd+Hha9f8gQWFDPutez4PySGlxyWq1AVzxcoVSWw09fX9wt4CEi38vGx58R9v76P+9v+3FFMqtOT80/mzKUbLHzQcOafORueN1DgSsB2nlcJBMBTQPr78vexlefO/xEWuj7JlbsAm71wmS4d/4ds3rkfpwFAqeTABxaLrSQ8BqQb8TuuVpjip19q1KrHDhw9Ywjnn8zpC9dZ5+59UDYIFJkqyBuiNYbngLSuT7nzD8Oi1u+RP7XcVXNZn9JMmj6XtxrOgfcBKNE0qC/fQz+BG4E0J4vF8hf+As/BQtYnVB5HffmN6vhTQvMI8uQthPcCKMHUgICAP8GjQJpRpkx+/+XjMLdh8eqTzAE52cKY1XD+KfsGHDzGipUoi/cDKFAhIGzw9vb+NzwLpP5kP7NNwCQ//ZItex5x+h6c/sccPHaWlS5XGe8JUCIv4LjF4u8DDwOpVl/72AvwTP87WLD6JGu2POJxN5x9+nMFEAQAhSoEbqJ9MKTOL38foTjK/PR97E/NcODkpZ0E4DoAKHQSkMCDgMLwOJB6nL/FVoFHp4lYoPrE15qZzV0UC+fuYE4AEgOBMlgf84mCpeB5IBVk+1urY6CPvkv9aFQunLpzkwVRIgiUGiTk7W2vDA8EeUy8zK8BGvzoG5qIB2fuPJNnzEOzIKDUScBLvgfXgieCPOD8xe5+r7EI9UvFKtXFjndw5K7RuUcfvE9AKZK8fKxN4JEgt8nLIvyKhadvArLmZjv3H4YDl6ltMGYHAEWvBCxCW3gmSGl94mWx9cSC0z/o8if/ACFMEQTKtg62d4aLghRz/ujrbwzqNWwGp60AYQOH4f0CynYNtNiCTJgfAMH5A2fIEpCL7Y47DoetAGcu3cBVAHBHENAPLguS8c4fx/5GISxqqOEd9bmrt9ilG3fZ1dv32Y27CezW/YfsbsJj9uDRE/bwSSJ7kvicJT5/wZ49f8mevXjD87e8ePlKhP47/Rr6tY+ePmMJj5+y+w+fsJNnzrFOnbux2rXrs4IFv+E9Fvzx3gFcB0BI+AOepXDRkuz0xWsGcfK32eWb90QHf+fBY+6gE9nTZ8/Zy1evmDv18uVLdv78BbZlyzY2bfpM1rlLd1a+QhVmtSEwAEgMhDyot6V+WEwGYeK0Obp09uev3Ra/5u88eMQePXkmfqGrXYmJiWzfvv1s4sQp7Of2HViewIJ4R4Hj1wEoEYScc/5ikx/U+RuEkqUr6Ob4/uqt++wu/6qno3ctOHupOnLkKBs+YhSrUbMOTgiA5D4BaBYEOSRq74sOf/j615TD53f0dIT/+vVrZgQlJCSwJUuWscaNmyMYABI6BqJtMCRBbwb7oLe/kShQ6BsxQ10TDp9z5da9Nw4/0TgOPz3dvHmLjRsXzcqWq4T3GaQzOwADhKD0nD8f6YupfsYjYvAIdd/h84Q9StZ7xDPwk5Lg8DO6Jvjtty7MJmTBuw1SmSKIUcJQKvrax16AvyCPsEiMhWAPYHEnzqrO6V+4fofd5uV3T8SvfDh2R3X9+g0WEhrOsmbLjfccpDgJEB7wICAPPB70+5e/2SZw538HC8R4NG/VTjVOn8ry6Gif6ushefTo0SM2dmw0y5U7H953kBwE3LBY/H3g+SBTpkx+/+UvxRksDGMye8FSjzr9i/xLn5w+Nc6BlA0EwsOjmJ8/5hEAMSfguMVi+Rc8oKGz/S1/4c0itmFBGJNsOfJ4ZNwv1eVTd73EZy/gmd2sq9eusQ6/dWY+vn5YA0bvEeAlbAgICPgTPKEx9SmvD52NhWBcWrVp79bOezfuJLDHvDYfmfvqSBasWAlzCdAyWJhiwvAg44k/+FAsAGMTPWWm4o7/Cr/Xpz74yN5Xn17xtscjRo5mdr+sWA/GDgL6wCMayvmjxa/RoSPgg8fPKva1f+veQyTzaURnz8az6jVqY10Y+jrA1hie0RC1/rby6PIHipcsp0BC311x4t2rpCR4VY2JrmWGDRuJ6YTGTQp8YbHYSsJD6lg+PvacXl7Wh3jhQbv2nWRz/NfvPBDr9SHta8eOnSwwbyGsEcP2CBAC4Cl1Wetv/5rX+l/Biw6I4WMmuNydj6br6WnYDvRGt2/fZnXqNsQ6MWYQcIFXh30Jj6kr52/+K3+4+/CCg2Ri125yuoTvHq/bR1KfvpXEr3H69uuPtWLIfADrTkEQ/gzPqQ99wpP+JuHFBikTAI/HX3bc8fP7fTh+Y2nixCnICzBkZYBtNFynDuTlY/8BLzRISe48BaT35L92R0zsS0LtvmG1es1aljlLdqwdo50E+FibwINqWBaLUBCjfcGHVKxSXZrjf/QETXsgUXFxh1iOnIFYP8bKB0jkPiQ3PKkG5evr+wWS/kBqNG7WOt0afjrqh+OHPtSxY8cxVMhwQYDt/FdfZf4/eFRNqfgfeJ/njXiBQWr8/GvnVJ3/zbsJ7OUr1PBDaevEiZPiFRLWkZHyAayruVP5DH5VK/f+XsJAvLggLXr2CX6/Xe+teyzxOQbzQNJ0+vQZliewINaSsdoFB8OzaiLpz1YHLyxIj6DQqDf3/HwU78MnifBokMM6evQYC8iaC+vJUEmBtmrwsKr+8rdl5ff+j/GygvSIGDSC3aVaftzzQy5o27btTLAHYE0Zp11wwle+vn7wtCqU1Wr9J39Ip/GigvSoVLkau33nDrwXJIuWLY8R+0pgbRmGo59/HvB3eFy1ff1bbDPwcoK0sAlZ2NChI8QRsBAkp8aNi8YaM9JVgEWIhsdVkXiCRgO8mCAtyleowo4fPwFPBSmm9r/8hrVmICwWew14XlU0+/H3obsZvJTgQ6y2zGzgoCHsJQb2QArr2bNnrHKV77DuDIPtLg2Ygwf2cL2/2SJsw8sIPqR0mQrs8OEj8EyQ23Tt2nWUBxopKdAirONO6FP4YY8d/dt74UUEHzX6+flX9vTpU3gkyO3avGUrkgINdRVg6whP7AF97WMvwB/AK7yEIOWRP01vgyBPKjgkHOvROKWBL3g+QC54ZDfK39//H7ze/yxeQJBM3nyF2N69++B9II+Lck6+rVoD69I48wJOZMqU6W/wzG47+rdNwosHkvm+Vl1269YteB5INbpw4SLLEpAD69MwrYJto+GZ3VPyVwsvHBDv33zsLCgoGLX9kCo1adJUrFMj9QfwEr6Fh1by3v9rwcwzL+/hZQO+Vn82ddoMeBlItaKR0jW/r4v1ahisty0Wy5fw1MroEx5hxeIlA9R/ffnyWHgYSPWKP3eO+flnw7o1TJdA2wK4agVkttjr4QUDWQJysq1bt8OzQJrRiJGjsXYNVRporQ6PLaMyZfL7Lx2v4OUyNrlz52dxcYfgUSBN6cWLF6x4iTJYw4ZpEGS7xq8C/gXPLVfin0WYihfL2BQqXJzFx5+DN4E0qY2bNotJq1jLhukSOA6eWwZ5+Qjl8EIZm8JFiottViFIy2rV6kesZyMFAT5CcXhwF0Rzl3mThfN4mYxLYN5CYk01BGld589fELtVYl0bhtP8KuAv8ORO1/xbB+MlMi7ZcwSyEydOwnNAulGv3v2wtg3VIEgIgSd3Juvf156fGzAJL5FRs/1zsAMHDsJjQLrSnbt3xUoWrHHD9AZ46eNjzwmP7oACAwP/yI13GC+Pcev8t2zZBm8B6VJDhgzHOjcWe7lb+wyeXWrin8XWAy+NcTv8rVy5Gl4C0q0ePnzIsmXPg/VuqAZBQgd4dkn3/nYrH7H4DC+NMRk+YhQ8BIRTAKA3nnxls3nBw2f09e8lLMLLYkyoTAqCcAoA9DksyDYTHj4dWSy2knhRjEnJUuXY48eP4RkgwygychDWvuHaBNsKwdOnrs94ycQhvCTGIyBrLnT5gwyn27dvM7tfVuwBxmIP93Wfwt1/1PHP/gNeDuPh4+vHVq1eA28AGVJduvbAPmC8q4DG8PjvHf1b/oVhP8YkOCQcXgAyrM6ejceMAAMOC6Iut/D87zL/hUF4MYxH6TIVxElpEGRk1W/QGPuB8ToEBsPzc/n4+PtTtyS8FMbCavNnhw4dxu4PGV6r16zFnmC0UwAqdff280XLX4stBi+E8aAMaAiCGEtKShInXmJfMNpVgHW+sZ2/j608XgTjUa58Zfby5Uvs/BD0VsOHj8LegJHBxur3z49BjuMlMNrRf2Z29Ogx7PgG1+uXiezx8Rj2+EQse3JqNXt6dgNLPL+NPbt2iL24d4ElPb3HP41fGcYeN27cFK/FsEcYrSLAetCQcwK8vW2t8AIYj/79Q+D9IPb0/Fb28MDM9Dk4iz05uZI9u3qQvXp4XfcBQfMWrbFHGDMhsKGhnL8gCH/mf+lLePjGImeuvCwhIQHez+B6ef9Sxs4/NeLmsKfntrKXD67wI4TXurPLihWrsE8YkzMmU/E/GOnrvx0euvGYNGkqvJ/hj/6fsUdHFjoXAKTg0eGF7NmV/Szp2SPd2IZKYnPkDMReYcgWwUJzYyT+mc1/pUYIeOjGokTJsjzx7xU8oNGP/s9tddn5f3hN8JTnDog5AzpQz559sF8YsixQuBAQEPAn/bf85XOR8cCNx5o16+D9DK4Xd8/J6/w/4OnZjSwp8YGmbbRnz17sF0ZNCLQIbXTt/L/4Isf/8EjnJh62sahTtyG8n8FFR/WPDs1TNABIPhFIvLRHvGrQqgoWKoZ9w5BYr/C2+H/R79e/l9AVD9l47Ny1Gx7Q0Bf/Sbzcb4Xyzj9ljsCh+ez5rVOaNFdIaDj2DcNWBNjb6zXz/3+9Lba7eMjGolq1mnCABlfixV1udf4peXJ6reYSBePiDmHvMG4uwI1MmTL9TY8Df/rgARuPlStXwwMa+d7/9mmPOf93pwFxc9kLjZ0GoDWwoU8BOuvM+Xv/m0c2D/BwDZb5X6IsL9d+DS9oUFHznocHZ3s8AHiXJBi/mb1+pY3pkwOCw7CHGDcX4I6/v/8/9PT1H4yHajxmzpwNL2jUpD+ejU/38Gpx/sk8PrZUEyWDBw4cxB5i6IoAW09dOH+r1fpP3u/4IR6qscidpwB7/vw5PKERc/6eP2GPji5WnfNP2VHwxd141duR1hD2EqNiu6uLXACLxdYRD9N4DBgQCk9oROf/4in/yl6mXuefgmdX41Rty/a//Ia9xNDjgoW2mp/4h57/Bmxr6WNnJ06chDc0ovM/vlwTzj+ZxPPbxTJFNWr+goXYTww/I0DDkwJ5f+P6eIjGo2KlqvCGBjz218qXf2qlgmpMDrx06TL2E8PPCLBW16r//4QfYezHQ8TQH0jvCX/3+YCfxZp0/u+CgJOreBCgvpyVAgWLYk8x9jXAdq3e/ZfEAzQeNiELu3v3HryiQUSlfmrM9neqQoB3K1RbC+Ef2rTDvmL4UwBbIe1N/bPYYvDwjEfzFq3hFQ0iscmPiur8ZQkCeA7D65eJqrHx6NHjsK+gJHCB1ur+A/DgjMmsWXPgGXV/5v+KJV7YoSvH//5JQKxqrgO2bduOfQW85nN0bBoa+WudgIdmzOz/69dvwEHq2fc/vc+/kmN06/zfywlIeulxe9N1Gq0r7C+GHxU8UiN3/5YvzV7W53hoxqNCxW/hIfWb58+e3zyuuyP/9KsD1omnHZ5WYN5C2F/A06++yvx/Gvj6tw3AwzImUVGD4Sd1meX/QPwiNorjf29+wLktHrd//QaNsb8A9bcH5l//f6FBBnhYxmT//gPwlnr65udH4M+uHDDUV3+qHQOv7Pfoc+jVux/2FyCOCqbmeirO/LfXw4MyJlmz5WZJSUnwmrrw/K95hv8ZXtu/yNCOPyVU8eApTZ48FXsMeFsSaK+h3uN/L2EDHpIxoWNKSPt6ee8C7+i3HE7/Qw7OYi8TrnnkmWzYsBF7DHiDt7BSnV//ZpuAB2Tg+/+BuP/X9Bf/3XOa6+PvbqjhUdKzR25/PGfOnMUeA96VBPr4+FjUV/tvsYXj4RiX9es3wpFqze/zWvfnN45qvo2vexsFxbq9PDAxMRGlgCBlMmCQ6qb+UYICHo5x6//v3bsPj6oRvXp0U5yC9zBuDpy6RioDcuQMxF4D3mK9rKopgZSYgIdiXIoVLw2vqvpSvgT27Foce3x0KZy4DDy/ecKtz698+SrYa0CKXAB7ZTW1/l2Jh2Jcfmz7MzysGr/0n9x94/QN0LnP/UmBs7l977jtWTZu0gJ7DUiZDLhEFc6fEhIoMQEPBQmAkIfv9PkQmxf3zot9+lHC54Z8gKNL3DYzoGPHLthrQEpeeXtnzqSGzn9BeBjGZtGiJfC+HjrWp+z9xIu7eOneMjhlHecDhIVHYq8BHyYD9vC0//+MEhLwMIxNXNwheGNFPf0r8Tj/xZ14sSvdk9NreUnaPDhgtTQJunNW8VdgfPRE7DXg/c6AFus57oM/9eDdv70yHgQqAB49egQn7erx/YtE8U755f1LYoJZ4qU97MmZ9eIxMzWhgaNVcX+AuHmK9weYN38h9huQWjJgGQ/W/gtz8RCMTZ7AgsZ02LwW/PWrF+Id8OuXz0QH/vrFE+4IHoojc189vsPL7W6wlw+uiPfyL26dEmvuqbd+4oWd7OnZjXzAzsq3Dn42HKnWJweeWk1vhWLv25o167DfgNSSAad4xPl/8UWO/+F/gCd4CMamcpXvdJRE94w77Mvs+a2TPHv+MHt2eS931tvfOGu+wdM9+6PDC/BFDlIvDeQBnlLavXsv9huQ2oCgB4Ig/NkDff9ttfEAAJUnafZqnX+p09AbyppHEh2QozSQEjOV0LFjx7HfgNSTAb1sVT0RACyE8QGVJ2npnv35jWP8i34DkuiAQlcBqxS5Crh48RL2G5BWNcAMtzp/f3//f/Cjh0QYH1B5kuob4vC7+KfntuKuHbipS+Bx2d/hO3fuYL8BabUGfmQ2m//qxta/Qn0YHRDREyap82ufJ+jRXT4m3AH3VwXMZUnP5a0KePz4CfYbkHY1lsVa3Z3Z/0thdEAsXLhYdYl8VEJHmzCcEfBYgyCeOCprvkpSEiYCgvSqAea4xflbrdZ/mr2sz2F0QMTGrlSN839xN/5Nlj4cEFABL+9flPX9ttoyY88BafGEKvPckfzXGMYGyaxfv9Hjjp9q75+cXgenA9R1FcDnMbxOeiHbe54lICf2HJDeKUAtNwQAQiyMDZLZtm27J7vxsOfXj2CuPVAt1PhJLuXKnQ97DkivGmCBwq1/vf/NMw5fwtggmX379nvG9z9/wh6fiIWTAervDfBMnt4AgXkLYc8B6TUFSvz884C/K5n93xyGBik5cuSo+4/8eQMfjLsFmkkIPLNBlvc+f4Ei2HNABgOC7PUUPAEQlsDIICVHjx5zb03/w+to4gO0lxDIW0y7qkKFi2PPARnlAcxWxPkHBAT8iRoOwMggJQfdOAr4xd3zaOYDNAm1mmavXesQWLgIAgCQEba73F1/psDxv60kjAs+ZO/efW5x/tTCF44EaLpDIJ8IiQAAKN8USCgof/a/xRYJ44IP2blzlxu+/M/BgQDtlwXyHhU0PtpZFSlaAnsOkFINEKRE97/DMC74kC1btil75//4Nsr8AMoCuQoWKoY9B0jIA7DtltX5f/21YIZhQWqsWbNO0VK/R4cXwnEA/cCD2dcvnji1HgoULIo9B0jh9ZeC8LmM2f+2ljAqSI158xcq1OPnJa/zXwGHAXRH4kXnrs3QBwA4UA3QQM77/wUwKnDnNMCn57bAWQCdNgea5VRzIHQCBNLzAKzTZXH+gYGBf+TDfxJgVJAaUQMHK1PuB0cB9Nwc6LzjuTPZsufBngMkngBYb3H3/anLAYDZx14MBgVp0at3P3mP/nmWNCb6Af3DTwESHzi0Nvz8s2HPAdK7App988lx/x8GY4K0aNfuF1kDALofhXMAhjgF4NdcjsjH1w97DnAkD6CPDNP/rAdhTJAWNWrWka/k79FNOAZgrFMAPtdC0vyLV6+w3wDH8gC8hB2uHf+bA/4DQ4L0KFjwG9lG+z4+vhxOARjsFGCrtLyY54nYb4CjJLk0HZAfIVSBEUF6WG3+LCkpyfVWv7dOwiEAg+YCZFwR8CLxMfYb4EwyYGnc/wNFuXbtustf/4+OLoYzAMbsC3B+e4ZL5OXTBOw1wJk8gL4utP+1bYERQUbs2bPXtbK/O2c9vgk/2DuN3do6nl1dM4KdjxnIzi4KYyfn9mfHZvZhR6b2ZHGTu7O4iV3ZgQmd2YHoNxyc0IUdmtSVHZ7Sgx2d0ZudmBPEziwMZeeXR7Er/OfQz6OfCycHMuwL8Pxx+vkxT+8xi48d+w1wdDrgGqfH/5q9hEQYEGTE7NlzXQoAaFSqOzfchH3T2c3N49iFmEGik4+b1I3tG/ubYhya3I2dmjeAXVwxmN3mQUHCgRlweuD9U4BLezJIkL3FMmcOwH4DHEwEtD50ajwwjRSEAYEUBgSHOe38X9676JYN9t7uKezSqqHsxOx+7MD4Too6/IygU4TT84PZ1bUj+AnBdDhA8GZGwMtnaa+ThKssR7bs2G+Aw1gs9lxOBAC2jjAekELTZi2dDgCenFyp3LH+/hmikz0xqx93vB096vTTDAbGdRRPB25sHMMe7ocjNPSkwGuH0gmUL7Bc2XNivwHOTAds50T9v7AIxgNSKFa8tFPOP4nfayqxkd7fM5Xfww/kd/RdVen007wqmNKdXVo5RLyigEP8kBnic72zYyK7tWWcGDBdWz+KXV03kvPmP+l/39g4WrzeubUtmt3bNVlTtqQOmLzgP/U8mdtnWEAWdAIETiUCznbU/3/C7/9vwnhACr5Wf/b06VOHA4BnV/bLfrdPCXgHojtpyvF/CAUuF2MHswcGDAQoP+L2tok8iXI4i18SISZWUpLlfheubShh8/DUHuLPOrskXLwKoiBCjcHBi9unUy+TvXGM+aITIHAuALjkYAMgmwDDAUfYt2+/o7V/7NGRRbJtnNfWj+YZ+d007fg/OhHgFQZ0hfFQ10mD3OFvnyAGPMdnB7k3eBv3GzsyrSc7uzhcPEV4sMfzFRvUDCs1JZzfi30GOM1XNpuX9ON/H2sTGA04wpQp0xxL/nt4Tb4Ev11TxK88PTn/lByf1Zfd3TlJV46fvvLPLY3kFRLdVWPn/TxP5PjMvuI1zP3dUz1mm5cJ1z5aL1ePbMQ+A5wfDGSx1pUeAFiE8TAacIROnbo6NvTnwnZZN83rm8aw/eM66jYIoCPwy6uHa/o0gI7c6Wifeiao3t78XaIS0Rubxrrd5k/PbvxovZzYvgT7DHC+HNAijHCgAZBwBEYDjlCufGUHTv/58X/cPNk3TnKQSjkDagJESXpHpvXg9BKdGH2ZH+dlhSKzeMOg6b3Fmn8lywxPLwjWXJJgAm+GdIEf8ZMNtRh8HZ3eS7xicl8gwBsDPXv03pLZGTMF+wxwhX2SnL8gCH/2tlhfwmDA0UTAR48eeXzq3/llUc45eX6FQF3/6D6YjoApy5wyzt908pvhVMb6rS3jeVAyTOwqSHfNcpUiUgBC1x6qd/y8DJMS7g5O7KKLUxh6P+5sn+ieksAr7+fULJs6CPsMcP4KgDf1M5mK/yHDAMDHx54TBgPOsHHTZmnZ/7zeWcmkslO8yY6Ur3r6cr+4Ygi/j57glu58FBRQq2E6Wt7v4ikBdS90lzNyBirFoxbKuruK4e8NBXWKlwQemv9eSeDYsC7YY4CLDYH8MksZANQIxgLOEBU1WFrzn1OrFL9rpq+1tBLqrvCrAk/36KeMczppoGsFp4MAfqR+h2fQq+2en05RdJuLwQMAyjdxS0ng3XPv1kyPn5pgjwEutgW21ZaQAGiLhLGAM9SuXT/j6/9Xz8XhJ+742qba7+Ryr9MLQlT5xUwnD1d4qZ+zgQAFAXd3TlbF34Xs+87mOuXKmmFus+eTU2verBneIrhlvarYY4CLiYC2ARJOAIRVMBZwBsEewBITE9Mv/7t/yW0bKJXO0XE7lZxp4b6chgU5czVAjXIo4PHkn5+68O1XfMZCR3EqI53iUEBHZYRkM8ozoNMU+u/UCIoaCNE/pyseuoaQ689F+SXutmtSYgLnPqtWoRT2GOAqSzNuAmSxXYOhgLNs2rwl/fv/q3Foa5tu0DKZHZvRx2Hn5I576bQgx6uEs6fse0qgpNJBau1Lsx2czQuhpElqE5w8AZJGOTtafeGJEsxnVw6wVw+vsyIF8mN/AS4mAtrOp+v8M2Xy+y8MBZScDPj0zAY4egmnAfFLIyS2De7Crm8Y7bEufs5WXaTVspe+3N3SlY8PXrq9NVo8McgoWZFKPBP2e6b/wqMjC9mLO2eZIGTG/gJcP6UVhP9NbwJgSRgJKNkPgAaewMlL72uwP53SQepa58lyQBq4JEc73hNz+otXCJ7sb0CVIJS8uP+DjpJ0veLpFsHX9qMJEJBtMmDh9CYA/gIjAZdKTXzs7OrVq6lP/3v+GI7dmbv1DzsccqdJX64JHuwKSHfuro5BJoerlgTGlNUZdKpBzZyof4Ea/nw75kZibwFydQRsk04AYJsIIwFXmTptRuoJgA8uw6k7AVUJpBwQRDX2nvzzXOdNkpxvatSRnVkYqvpGRvd4AinlHajhz7JodC/sK0CuE4DR6bUA3gMjAVdp3Lh56iNNbx6HQ3eSC7GUvBbk8Wx/4ha/O49zYojPUd7B8PbW8XieDjI66GfsK0Autqbl/z/j//ApDATkKAdMrS1w4qXd2NBdSLhT0yAgaqRE2fRSv/rpWN1TiXRap3u7BthXgEyVANYE7us/SaUFsI8FBgJysWjRko87AJ5Zjw1dZ0EJ5QKkN4WRMvup/A62cp7G31fGngJkbAls+TK1CoASMA6QixYtf/goAHh8dCk2dB1CVwI0BfHjeQVd2W2VtSrWIqW+KYw9BcgYANgKpdIC2N4UxgFyXgMkJCS8FwA8PDgbG7pO+fBKgJIVqRMjbOM6/v5ZsKcA+a4BLPZ6qc0ACIJxgJzMnDn79w6AiU80vQnf2zudXdg4nh1aPkwsy1o/LYStmNCPLRnbW8zSXjiqp/ifi8f0YrHRfdmaKQPYlllhbO+iwezEylHs6taJBrgDf3MlIA4p2uFa++X7vCfAhU3j2eHlw9nOedze03+3N9l6wcie4n+SvZeP78tWT+rPNs4IY7vm88Y+McPZxU3R4s/QfPInf+ewlwCZZwL0SK0CYCqMA+Tk+1p13wUAt29eU72Dj1s2VHTiQ3r9yDq2qsvqVasgHr9mz5Zd7G/gqj18ff1Yrhw5WdliRVn96hVZ59Z12dDebcXf8+DSoeKfQRenARIc74N9M9jR2BFs2bg+bES/dqxrm/qsYc1KrGzxIixX9pzMx9d1e9Mzo2dXokghVqdqOfZL81ossltrNmd4N7Z7wUB2S+WliMSmmaHYS4DcvQDGpxIA2LbAOEDupkDnz18QA4BTx4+qyEHNEL/Mo8M6sF+a1WLlShRlNpu/x+1FfwZygOSoJoZ3EIMCvZwO0Ff5tIGdWZcf6rEqZUqo4libgoxihQuwNg2ri6V2dLKjtiBsalQn7CVA5isA29rUpgBegnGA3EREDBQDgB3btnl0Iz21drTo8JvV/pZlDcimGfvlzpmLtW1ck80Z1o1d08BUw3cDgvgR/MzBXVibRtVZnpy5NWPvzJkDWNNaVdik8N/YuQ1jPW7HoA5NsY8AuTnznvMPCAj4E/8/X8MwQG4C8xZiL1++YjExseKXtzs3z9NrRrPBvdqw8iW/0YUt6YSgSa3K4pWBGq8LyOmPC/mF1ahYWpYjfDWcENSsVIZNiezIbmz3TEtgClixjwCZewG8oL4/v48ANtsEGAYoxfLlsWzuvAXs+MqRbrnLp+QwuvOV495ezScD4V1aiU7X05MLKeGxZb2qzGr106296WSg648N2MlVo1ACCDTPVzabV4rjf3sZGAUoRc3v64oVASsm9lP065McYmCuXIayLTmmkI4t3H49cGvnFDYuuD0rVqigoexNQc5vrerw06UxituYqhgwBhgokgjoZf8mRQBgawWjACWTATt16srGDvhZEcff55cmzM/P2LXSlO0+Mugnxa8Gbu6YIl6r5Myew9D2tnPH3O/XJuz6duV6HVAJJPYPoEwAYGucMgEwBEYBSjcGolIvOb+ORvX/mWXJnBX2TQF9ka/iNfFKOCQqn8ubOzfsnAIq66TqBiV6PFCPA9gYKIK30CflGOCZMApQ/CqgchlZNkbKJaBSMtg0bSj7/tJmedrw0s+h5EPYNZ13mycLHlsxQtYAYGCPH2BboFAAYJv8exKgRVgPowB3fC3RxrZv8WAW1aO12Gzn1xa1HaJ4YXXcOdO1Bt3P+tnfwq8gMnMCsmQV7+XVkhBXOH8+Vvvbck5fK1QtX0pd9vYje2d5Y2//ANHedAqkhj4OyTkCjr7TBE38mzGos5hbkRwAUPkn9g2gzBWAsCLlFcAhGAW4YxP/uen36r+u4I6GuvW1ql+N9e/QjE3gTXlieLtZajO7fW4ETwKrzbJnDeBOyC9V8ubJyTvOtWSX+Zcz5SgcjhkmtgWmI10qk6O74xZ1q4q/B5K83jhN6tZHJW99uW3IRtTydztvzHNgyWBeC9+EBebOkaa9c2TLyrq3rc/OrBvDrmyZILZepqY+9Myonj+kU3P2Y+Ma4qmR2q+M6Ipl88wwMQCgplDYN4BC7EnRBdB6BQYBRoRqvakTIOUn0BfY/sVD0kykWzCqB8uVM1uajuhDihXOx3bMi8gwl4HmC8we0pV1a9tQ/LPouXxRzFPg3feSux1SUHVn99RUbbNldhgrWjBQsr0pSFgytlfGTaFWjxKDMQruqlUopbryxYAs2dgJXm6I4BAo1gvAYo1P9v+f8MYAz2AUYBTy5cktXj+QE6AhPVLuYyeG/yrZEX34dbp26gCHKxtmDekinhLQcbceKhToC3zO0G6Su+utmhTEsqVzypIW/v7+vH1uR4crG5aO7SO2hc6WNbsqbPZTExz/A0WbASWI3v+LL3L8DwwC9E6BPIFiuSAdDTuasb16chC/1/d3KgAgcufM7nSCGJWZTY74TXPdDHNwp9+JB1kU/Dg6lY+GMuXIntVpe2fJnJlPDwx2yt5390wTJw1SIymPvq95A7FugaIEBgb+0eTj42OBMYBecw6a1/lW7FTnbJnWzR2TeSJdHqedUTJ1q5V1OSuc/h4NalRS9RUBZcPTyYqz/QjoOVWvVNJle9P1y+00rhakQgOj6OTCEwmGlEiKNQwU3R8tli+pCVAeGAPozfFTa1qqNnDV6Y4KaueyM0rG0auA9L6QKUFRbSWeG2aEuvx3i53QVzZ7Twj7RRZ7x68fJ7YBdmeuAPpbADc0A8rKewAIZWEMoCfHT4l8ctVilytRWDaH9HPTGrLWia+ePMDjrY/lcvzJtG5QVTZ7f1uumKz23rt4ECtdzD29+f39cAIAFM4D8LEX4z0A7PVgDKAH6Hhczg0/fv1Y2ZwRkS9PTtm7xZ1ZO9ZjiWvUY0DuwUKULyGnzS9uGi/rn5GmApYsWghf/0AHVwD2GnQF8BOMAfRA/e8qyrrZr5sWLKszUsIhEQXz5fWIven3lfPvcXbdWNntvXlWmOz2rl+9onKJWfxEZ3iftljPwB3dAFuZvCy2fjAGcCcNa1bmjXJaix3QqDFQRpQpJq0hilythpOhmnK5HZLc7WIJT10D0O8r59+DchvktndMdB/Z7V2rivQKAeovQSOE04Ma/1APCOpDQSWJaAEM3JIDYLF15wGAMALGAO6EHLojG25wx+aSfm7F0sVl3eipFl1uh0RfuXI7JKlHxh9+sV9aPYytDW36EUFNK0nOVJfz70HOUG570ymO3PZ2ZA7FmP7tHf75VLmCfQIonwQoDKQ2wLNhDOBOKJvakRKtiK6tJP3cogXzy7rRH4kZLqszoqZAD/bJOzWOfh51M5RiH2q3+17+wJLwVAOA0BZVJD9LR2v804NKB7MGZJHV5tT1T+4AgOwo1T7zRnR3+OdTzwrsE0D5KwBhCm8DLCyFMYC7oT7vUjdEmnMv5WdSC1W5k9IKydADIJlG31eU3RlR10CpNq9StsT7UxXnBqUaAAz+UXqJodTOflKpU7WMbPYuViifImORc2XPKdk+G2c4loNwfsM43beCBqphHp0ArIQhgLuJDusgeVOcObiL5J9Ld6hybvZ9f2kkm0OaObiz7M6Ieh1ItU2jmu9XScRN7ZFqADCuw/eSf+buBQNl/ftMjuwgm71DOjWT3d50SiH1xIVwNOdjOR9ghP0BuIml1AdgAwwB3M1vreo4VO8u9edSIpmcG/7pNaPTnfwnlRJF84ttZuV2SDEOOIwPbb43ulOqAcDcHvUk/0zq+ifn3+fWrikODQBK87qFtxI+v3Gc7PamSYNSbUOBgqPPPKxLS+wPwF1XACt5HwBhG4wB3E2FUtKbtByNHSH55y4bJ3/W98h+bV1yRjSgZs3k/oocR48O+lmybaJ6tH7v39056pdUA4DVwU2Yr8SvXCpZkz2o4Zn7rgYA0aHtFbH36kn9Jdubhk45XGL4XUXsD8BdSYAbKAdgL4wB3A2NOpXaL55+ndR+7EN7y++QKBegffOaTjuj4X1/VMQZETTVUKrN536QkLZ1WNtUAwCiQC5pzYVorK8Sf6+BPFhx1t6/taqlmL0dCbhoLoKjP18t0wiBAToB8o9/ygE4BGMAT0Cz4KVujGWLS+sF0KZRdUU2fsp279GuvsNT6caHtFfMGTlakkbDbVL+u5sGtk4zAKhZqqDsJznOnLyQDR2xeR+esyF3pUVKaGSwVHvTNERHfvah5cOwLwB3stdk9rKdgCGAJxgf+qv0jbe5tI33m0IFFHW4MdF9WZnihTJ0RJTNvmfhQEX/LHS/TCcpUksvP7yPXh/ePM0AoGOdspJ+Lp3M3HFx6l567JgXwb6vUjpDe5cvWUTs26CkvYlS30ifBTAuxLFhRFMHdsK+ANyZA3CI5wBYz8EYwBO0b/a99M0xSvrmeEGBdrsf1t6vmdKf9fq5Iav1bWk+IKagGBQ0rFGehXZuzjPjoxR3RMQW3uZWqk3Kl/zm/WuNAzPSdP7EmPY1JP/sTTNDFf+7UiAQ3LEpb8NbTrQ12bz2t2VY7/aNxCmLzo57doQrWyY4VAGwbW6EYtc5ALieA2A7yXMArFdgDOAJ6GvKkS5xSjZf0SJSGyQRXX6o934Qs3daugHAKp4IaBekjb+lts5GsPei0b0k29vOT2YcrQAoW6wo9gXgxhwA6zmeA2C9BWMAT+Dr6ydOV5O6QRYrXEBaYlqzWoZwSNTYR6qt5wzr9n5i5e4p6QYARLXi0uz9bbmShrA3za6Qam+aF+DIz762baJDpwsAuI71Ks8BsCbAEMBTrJ8WInmT7N+hmaSfmTN7DskVBlqFZgpI7RhHv+7Djn13d07OMADoJ3EmADmu+PXjdG1vSgJ1pAPgkF6OVX6smNAP+wFwdwBwhwcAQiIMATyFIxslZbFL/bm0oerZIY3o106yLcqVKPpxAuGuSRkGAEv7NRSTB6X8HtSuWc/2XuVA/T9xOGaYIgOvAJAvB8D6kPoAvIYxgKdoUquyQxslOTMpP7dZ7W9164wo4U3qdQhB3eU+CgAknAAQtUpJG3xTvHBBtyTieQp6n5xNuJRCjYqlsR8A9+YAeFmfm2AI4EnoWNWRjXJieAfJx9KO9mHXCjEO9ouneQEfNVeSkANAjPy5uvRTl4n6PHWh98iR+3lHy/8oWdDPngX7AXB3APCCAoBXMAbwJNTqV3Kv+J1TJHdLU6pLnceT/xxo/pNWpcWDPdMkBQBEqaLSmgJVLV9Kl/Z2pPlP5swBDiW2ig2ZeBkl9gHg/gBASKQkwOcwBvAkMwY5NiWv10+NJFcZHFw61LClaAS1rk3rGkFqADCyd2vJv9+Ssb11Ze8DS4aI75HUv3/n1nUd/j1oRgP2AeCBJMBHdALwFIYAnsTRlqnHV46UvCl/X6WsbpwRnX4UyJvXoa/R69snpfnz0usEmJLTyweyHNmknboUzp9PnOinF5tTP39H3mUKGBz9PepWK499AHjiBOABNQJ6BGMAT1KmWBGHN802DaTfTVPegB6cUbe2DR2ya+/2jdP9eRujWkkKAM7HDGThXaQ3HaITGj3YOzqsg0P2blCjklPlhRSoYR8A7sd2l8oA78MQwJNQgtXVrRMd2jjjlg2VfArg55eF7V88RNPOaOnYPg7ZlJLKPqz9/5D0pgGm5MyScLFRjdTcC+o7QImKWrY3JU46mpi3bU6Ew78PtQvGHgA8dAJwk04AbsMYwNMsd8JhdGhZR/LPp6PpCxvHa9IZ7V08yOGvxKAOTTP8uTvH/CopADgxb8Cb3gN9pfceCMiSTbP5F+c3jGOF8ud1yN6OlrP+Pvb4B6x/4KFWwLZrFABchjGAp+n7axOHN09y6ORoHKnPdvSkwdNQhURgrlwO2TJPztySMtH3T+giKQA4NK3Xu3K1YoUKSv5z5MuTW8zX0JK9aeCP1F4TydBExCMxw536/Wp/Ww7rH3goALDG81kAwikYA3ga6mvvzAZKNdeO/D60uWd0NK6mY2hHnT/xYd//tDgyo7ekAGDvhN+rNGjyniN/FgoCnEmM8wTUzrhs8SIO2zu0cwunfj8ao4z6f+BBjpj4SMA4GAJ4GvqKciZ7nMrZKNPfkd8rf2Ag2zkvUtXOaPGYXk4lhzlyFH1yQbCkAGD7iHbv/XudHBxbS6c0y8b1UbW9t8+N5O9FHoftXbpYYdGRO/N7rpsajLUPPIe3bTcPAIQdMAZQA/R16cxGenrNGMllaikDjsG92ohZ2GpyRDd3TBFH9zpjPzotcCTP4fyKwZICgPURzfmvn/FeOWLJooUc/vN1bVNf/PupbcjPoJ5tJM88SAmN/KX8DGd/75COLbDugcfgvn+jyWwR1sMYQA04e5SaPKzFkYYtydAM9tX83/W0I3qwbwabNaSLU1+hBDmwDTNCHfo9r28cI7kZ0J0dkz6qwnDmhKJAnkA2Z2g3VcwNoHeGSlCdfV+nRHZ06fevVKY41j3wZACwggcAtuUwBlAD31Uo7dKGOqZ/e5d+b+pi5+4Tgdv8+HjqwE6sRJFCLtnOmV4H9/dMlRwAXF7zcZJbbHRfp4Ku5BbF0wZ2Fv/+7v7ip+dcrUIpl+zds11Dl5MNnbUdALIEABbbAkoCnA1jANXkAex07YiYyt9c+TNQ0hpVJGydHS5+lSvhhCibnr4+f2tVh2UNyOay3VKb9ieVzYN/kBQAnFoQkuq/T8EL1f07+2en3gKUU0CnMGQXpU5XtswKY31+aSI+X1ft/UP971x+N+aP6IE1DzzNVB4A2MbAEEAtrJLhOF7qrAApzqlprSriHTFNuju5apTDG/+9vdPF2fCU1EeOmtq+UmMiuewlpd4/PfZEd5IUAByYlHZlwQR++uBKEJCMv38W0T5kJ/pKJ7uR/Rz9wqfntGJCP/G50fOT2sBICi3qVnX4z5QaFPxhvQMPdwIcSgFAGAwB1IKrDi0ZR1rXOnrXTvfYFUsXZ3W4s6I58fRF2KZRdfE/KQu/VpVyYs+BvLlzOzRG1lGoiYyrdjo+N0hSALBlcJt0f87MwV2cSqST0iWS7Ej2JLuSfVPamxw8PQd6HvRclPgzJENTAeW6InJkpgMAylQBCH2pCqArjAHUQmWeGCXX0S9NGRR4prbebES141Jr/TNsprRqiAOJgOk3UaIqDkerMbQABSF0miDXe0kJlFjrQAVJgL+YvHzsP8AYQC3QFxz1nZdrs929YCArWjC/buxTrHABtnfRYPnyEXZNkhwAxC+PyvDnnVo9SmzqpBd758qRk62cFCRrTsKIfu2w1oHnAwAfaxNqBFQbxgBqgmbey7nhUltcmtMuxz21p6A/e9cfG7icJJnqUKChP0rMA+gqOe8hqvsPYlKnlt9Dut6huQBy27tOVbT/BWoIAGzV+AmAUA7GAGqCnLUS2eCbZ4aJd8laswfVi9PUOKVK4w5N7ykpANgQ2ZInQUq/Az+8fDirV62C5uxdpEB+cfqiEramAE6P11JAe5h9hOImi0XIDWMAtW3ASjk7akBDjWgcGWrjKajb3rwR3RVvmiO1IyBxafUwh38+lfhVKaP+awEqEaTZEnJk+cs11hkAxU4VLfYs/ArA/ysYA6gNpafIUTnfwlE9Wc1KZVR31E9HxNQ7313d8u7zZjzrQpvJeg2QVu97ytpXWwMcqiCgpkRK9SFA+R9Q5QmAOeA/psDAwD/CGEBtTHCis52z0FF1SKfmHj0VoKEyEV1beWx87p5xv0mbCxDenN3b7Voewtl1Y9mw3m1ZhVLFPGbvgvnyst7tG8uaUCkFORoRAeA61pcmk+lTE4k3BLgLgwC1JWB5whFSMDA66Gfx96cMcKX+fjS4p1X9auKRs6ecfkrOLI2QfA2QVldAZwc5TY74TazrV7I2PjsvT2xYs5IYeLgywMfVihSsbaCSAOCqKVlmL+txGASoiSyZsyp6FyvdQY0Wj+OH9PqRdWhZR+xUR33sc+fMlW7TGZoURw1saL58/eoVxcRGKv+ijoL0Bay2cbj0Vb8uTNo1ALUPfqDQzASaZkjdIEcG/SRORWxQo5JoQ7KlPZ3kOXoW9Ezo2VBjoF9b1BZr96mjoBoCLCKyW2usbaCO438v64F3AQCNBYRRgNqgO2M1z5BPLnmjMkMa7kLQf1dD4OIM+yZ2kXwKcHZphEf+jNSJL9nelzdPYNe3T9KMvbVYgQJ02wVw1bsAgLcDngWjALVBw1u06Ei1yqVVQyUHAJsH8VOAvdNgN4mcWjta030ogN4CANvk308ALLYIGAWoDTrOhfNwHwkH+NS8IT9KDgKOzQmC3SQyLrg91jRQTxMgiy0o5QlAOxgFqLH7Hd3Bw4G4MRlwcZjkAIByBq5vGgu7SaD2t+j+B1R1AtAyRQ6ArSqMAtQIZcnDgbgPOtbfGNVKchCwbXg7XAVkwNWtExWdUgiA422AhXLvAgB0AwRqpf53FeFE3H1fvTBUegAwrB27vW0C7JYO0/lUSqxloLougMnKlMnvvzAKUCPUN52yvuFI3HkKMF0s9cvI+R+c2oM92IOv/4xoUqsy1jJQFZ9/HvB3Uwp9wv/PJzAMUCPUsheOxL2cjxmYTjfAFix+WRTsJHESpR3Df4CaegBYhHumD8X/wREYB6iRn5t+D2fidmaw3WM7pHLk/xO7uWUc7CORuXyYE9YwUBl7Pw4AvIXFMAxQIzmz5xCH98ChuJdbW6PFr/1k5x83rQcS/hykRd2qWMNAbU2AZn8UAHhZhCgYB6iV9dND4FA8URa4JIKtj2jOzi0fCHs4yM0dU5ifXxasX6C2HgADUgsA2sA4QK10b9cATsVDVwF3tk+EHZxgzrBuWLtAhSWA1iapXAHYy8A4QK0UyBMIpwI0BU2UxNoF6rsCsBb5KACwWPx9YBygZnbMjYRjAZrJ/vez4/gfqLEHgOVLUyr6zOwlJMJAQK3079AMzgWg+Q8AzpYAegkPqOw/tQDARDOCYSSgVr4pVADOBWiCutXKY80C9d3/ewk7TGmJZwfOgJGAmtm9ANnoQN1c2Dgevf+BSisAhOj0AoDuMBLANQAAGP0LdBkAdEg7APCxVYORgJopnD8fnAxQNZXLFMdaBerMAfCxlU8zADCbbQKMBFANAIBzHF4+nFl87FinQJV8/bVgNqWjz/gvegpDATXT++fGcDZAlQR3bI41CrRXAfB7QyDrLhgLqJl8eXKzhP2YDQDUR4G8ebFGgVobAG0yZSRvb9sYGAuonTVTBsDhAFVB7yTWJlBvAGAbIiUAaA1jAbXTvhlGBAN18WPjGlibQMU9AGyNMwwAzGbffDAWUDtZMmdlt3dPheMBquD69kmY/AfUnQNg9s2eYQDA+wT/hf/iVzAYUDvzRnSH8wGqYEJ4B6xJoOIEQOuzwMDAP5qkiP8Lh2E0oHYa1KgE5wNUQSXU/gN1s9ckVdQuEAYDaofarcavHwcHBDzKnoWDUPsP1N4BcKTkAIAnAraE0YAWGNLrRzgh4FG6tqmPtQhUXgEgNJQcAJjN1mwwGtACxQpjQiDwHLd2TWEBWbJhLQKVJwDaBJMD+szLy/oQhgNaYMusMDgj4BGmRnXCGgQqx3onww6AH50CWIT1MBxATwAA0qZCqWJYg0DdX/8WW4zJUfE7gxAYD2gBqr++tm0iHBJwK9v5UCqsP6CB+//eDgcAFou1IowHtMLYAT/DKQG3QidPWHtA7VgsthIOBwCCIPwv/5eTYECgBUp9UxhOCbiNi5uimV3IjLUH1N4A6LnZbP6ryRnxH7APRgRaYdPMUDgn4BaierTGmgNaYKvJWfG7g0EwINAKrepXg3MCinNv73SWN3durDmghfv/YKcDAC8fWzUYEWipM+CZtWPhpICizBnaDesNaKMDoI9QzukAwGwO+A+MCLREcMfmcFJAUcqX/AZrDWiBV59/HvB3kyviSQQHYEigFXJlz8nuYEwwUIg1UwZgnQGN1P8L202uystii4AxgZaYFP4bnBVQhPrVK2KNAW0c/1ts/VwOALy97WVgTKC1+QAJ+2fo0gGdjRnGDkwP0Qxnlg/Tje33LhqMqX9AQwmA1iIuBwAWi+UvZi8hEQYFWmLJ2N66c/6nlw5l039toi06NGXXN0frwv6t63+HtQW0Uv+fEBgY+EeTHPK22NbAqEBLVKtQSncBwNZRPbUXAHAurBqledsfiRnOfH39sLaAVsr/lpjkEr8G6AyjAq2xYYa+GgPFcyekNee/rP9P/DpmuuZt/0uzWlhTQEMBgK2dbAGA2WzNBqMCrVGnajndnQKsG9xZM85/Vqdm7OrGcZq3+bEVI8QeE1hTQDMJgF6CzSSjPuF5ABdgWKAlKGFr6+xwXQUAt7ZNYnO6ttREAHBi4SAM/QHA/ff/x01yy8sijIBxgdao/11FfSYD8uQ6NTv/PZP768LWdPePr3+gsfK/SNkDALOPrTyMC7R4CqDHIUF7eUMatTr/HeP76MbONF8C6who6gTAx15M9gCAygG9LdbHMDDQGt9VKK3LngDbx/XBl7+C7Jofhbp/oLHjf+G+yVT8DyYlxEsLFsPIQIssH99Xl0HA7on9VJPwp5c7/2S+r1IWawdoLft/lkkpeVnsTWFkoEVKFyvMHuzTZ3fAY/xLlRywx0r9gn5iVzaM05VNqZEU1g3Q3P2/j62OYgHAm+mA1pcwNNAi40J+0W2Peiq3Wx7c3r1f/b81E3MR9BZY3ds7nRUrVBBrBmgt+/+Zv7//P0xKCl0BgVbJkS07u7Jlgm6DgIT9PGt9bgSb36O1oo5/Bq9A2Di8O7u5daIu7Tik149YL8DY3f/SvAbwsf8AYwOt0rVNfd1PrLu/bzo7NCeCLenXVlbHP7tzC7Z9TC/d9PZPjVNrRzN//yxYK0CL9/+NFA8AfH19v+C/WRIMDrQI9XO/siVa90FAMhfXjGI7eLXAoj4/Oun0m7P1vPsgJfjd3zNd9/ZqWqsK1gnQ4vH/C16p9y+TO8THDG6C0YFWWT+pn3hcbrRZ9je3TWTHFw1iu6L7sjUDO7HFfX9kc7u2EjsLzu/Wmi3u05atCu/AtozuwQ7NChODBz308ZfKvBHdsT6AVlv/rjC5S2aL0BZGB1pl2chu7MLaUYYLAEDa3N0zleXOmQvrA2jzBMAiNHNbAPClIHzOf9NXMDzQagBwYG4ou7VjEpwfENk2MwRrA2g2+99qtf7T5E7RkQOMD7QaAOyfG8IOL4xgCfumwwEanEsbx7IlI7pibQBtHv9bbAtM7hYvOWgI4wMtBwDEqRXD4AQNzO1dk9mBeaEIAIBmsVjsNdweAFDDAf6bP8UDAFoOAIjLG8fAGRoQamB0eHGk+A4gAAAaPf5PoDk9Jk+InwLMwUMAWg8A6AsQ+QDG41TskHfvAAIAoNHa/0kmT8lisVbCQwBaDwCIQwvC2b090+AYDcL5daPee/4IAIA2j/9tJUyeU/E/mC22a3gQQOsBAHFsaRSve58BB6lzrvFGUPvnhSAAABov/bOe4074U5MnxWcDhONhAD0EAMTJmMEsAU5St9BVD135fPjcEQAA7R3/C31MnpaPj78/HgbQSwBAnFmJygBdNvvZNYXF8aue1J45AgCgMV77+PhYTGoQ70K0HQ8E6CUAIM6tGQGnqacRvzy/49CiiDSfNwIAoK3jf9tak1rEMxFb4qEAzQQAo7pnGAAQ59eOhPPUifM/8rbcDwEA0Efyn1BfNQHA558H/N3Ly/oQDwZogWkRHSQFAGIQsAYzAzQ9Gnkvd/5LojJ8zggAgHaw3vFY7X86EwJH4cEALTC0548sbmG45CAgfjWuA7T75R8l6RkjAADaaf0rRJnUJrPZmg0PB2iBIT3asAvrxvCBQCGSgwAxMXA/nKpmnP/uqeKsB6nPd/FwBABAG3z9tdVuUqP4H24rHhDQQgBwbfME3glumGQH8a5EEMOD1N/ff+dksbGTI892UsgvWBsAyX+udQYU6uMhAa0EAFc5hzNIDvuQo/w+GR0D1cuNrRPYgflhDj1TShAc2P0HrA2ggeQ/Dwz+kSpBEP7McwFu4UEBLQQAxOUN47nDCHXIYdDX5c0dE+Fw1TbWd8Nofq3j2LM8MC+MvwPj2MBurbE2gNq//q8FBgb+0aRm8dnE/fGwgFYCAIIy/R1xGskDhDBFUB1Q+2bK0XD0GYr9HlaPEt8BBABA/cl/tp4mtcvbO3MmPqLwBR4Y0EoAIOYDxAx1yoGcih3Kx8oiL8Bj3f12T+HXMpFOPbuT/JknP38EAEDVX/9eQmKmTH7/NWlBvDPgNDw0oKUAgPIBpNSLp3olwDvM3dyOccLu5urmceygg/f973I5eHng1U0TEAAArZT+jTdpRbwzYCAeGtBSAEBc2Tiexc0Pd8qhHHjbPhjTBJWHTlxOxzp3YkPQPAB61imfPQIAoOoAwMuW1aQloSQQaC0AIC6tG5vqtDhHMspvbkeCoJKjfA8tDHf6+dCzvcif8YfPHQEAUC+2NSatyWKxVseDA1oLAIhzq0d/NC/eUSgpjdrQwmnL19XvVOwQl56J2Nr5bdIfAgCgndI/a0WTBvUpP7Y4iQcItBYAEGdWDnfZ2dD99KUNY1jCAVwLOJ3hz213cf0YbstQl5/H6RXD03zeCACAKvEWDnFf+okWAwCeCyC0wEMEWgwA3lQGuP7FSRxeFMmubhkPh+7ocf/WaIcbNaWZ8b98SLrPGgEAUOfXv4qm/jnVGMhivYoHCbQYAFzjWeLHlw6SxQEldxG8zp0anHsG3fy2TWTHlw2Uze7H+DO8tmkCAgCgsdI/23mTqfgfTFoWrwjohIcJNBkAiEFANDu2RD5nlBwIXN08HsOFPnT82yewE8sGy2trXu4n5TkjAADqO/63tTNpXfwU4H95E4P7eKBAkwEA9QjgQcDRxVGyOqbkq4HLPEfgwT4D5wjsnykGQ8eWDpTdvkfEWv9oBABAg87feitTpkx/M+lBvIVhEB4q0GoAkBwEHJHpPvrjZMFQFr9quDjFziiOnyokLq4fzUv6IhSxKeUOXJHo/BEAABU2/ulm0ou8vb3/zdsDJ+DBAjUwvHdbhwMAJU8C3r+vjhIrB+7psISQMvqv8zp+KudzpdeClF4Mjjh/goJCrA2gDqx3/P39/2HSkzAkCKiF8cG/OBUAuCsISO4seGL5IHZp41hNjx9OeJvUd5aXVVIHPqXtdtSBY/+UjOr3E9YGUMnXv627SW8ymwP+4+VlfYgHDDzNvKHdnA4AklHizjpN5r1JHLywdpQ4cyBB7cf7vFUv3eufXjHMLU7/99OTgU45f2LmwM5YG0ANXf/u6u7rP0VfgGA8YOBptkwPdTkAoOFBJ5YPdl8Q8EGDIfq9L6wbLX5dJ3g4iZBOKKhFb/zqEWKg4moXRWc4vmyQ+EycfZ5rJw/A2gBq+PrvadKr6BQAuQDAk1itfjz5bJzLAcC7ZkGxwzwSBLx/XRAqjsQ9vWKo2DXv+tYJ7O6eqbKXGdId/t1dU0Rnf2H9KPEunyYhevrvf5o/A1ef49nVY5iPrx1rBHj065+q5kx6Fj8F6IMHDTxF6W8Ky+b8k4lfNYIntYV43BGmNvjmMHfQlEtAwcG5NSPFAOHKpnGiE6dAgWrv6RSB/pP+N/3/9M/p19Gvp3+P/n1y9BRoqOrvxznLbS/Xc/ymYH6sEeDB0j97Z5PeRfcbVOOIBw48Qbc29WUPAIgLa8c4PZseOHcNQjaX8xn+1rIO1gjwVOb/VbPZ/FeTEcRrHH/FAweeYMX4fooEAMTlDePF5j5w0MpCvQMubxwn+/NbOroX1gjwzN2/j/0Hk1FksVj+wq8CLuHBA3dSKF9exZx/yjJBudvZgpTJfoOdzvSX8uzy5s6NtQLczZnAwMA/mowkPuWoOR48cCeDuv+geADwLqls1UhFm90YDbJlvIz3/WkR1qkF1gpwK2aLvZ7JeCr+B/6XP4oXALiDXNlzsvg1Y9wWAIhXArzaAFcC8hz5X9owzi3P7PSqUSx71uxYM8BNzl/Yz53hpyYjymyxVcBLANwBdXpzp/NPOU3wVMxQj9TGax5us1MxQ9z+zIb2/BFrBrgnAPARipuMLF4RsBovAlCSKmVLKHZv7EiVwCE3dsXTOnELw9nFtWM88qyubIxmFUp+g7UDFC77ExabjC6z2Tc7N0YSXgigBAFZsrG98wd61Pm/d8TMTwMO4DQg3dp+OjHxdMC2a24ky+wfgDUElCr7e/mVr6+fCRLLAqPxQgC54euL9/3vrhrnn8yl9WPZkSVRcPgfTfGLEhsQqeU50XwAdAcECnX9GwrP/3tZ4JcYFATkxOJjd3rsr7uIXz3SrcNyVHvcvyCMneNVE2p8RgO7txbfJawpIGfL36++yvx/8Pzv5QLYOuHFAHI5f9q41ez8U9ae09S8gwYsGaRufqdXDPf4cX9GhHZqjiAAyDjwR/gRHv8DBQQE/MnsZTuBFwS4OuxnbNDPmnD+7wUCG99UCxihdwD9HSm7/8rG8Zp5PsP7tOXvFoIA4KLz97Ie5O7uM3j81HIBvISyeEmAs1D99jLezlVrzv/9DPTxYiCgx7kCdMpxKnaophx/ShaO7MGyBmTDWgMuZP5bi8DTpx8ELMKLAhzl27IlWdziIZp2/u8FAuLVwHBd5Ai8Oeofxo/6x2v+uexfOIhVKlUMaw44cfRvnQ4Pn2FCoL+P2UtIxAsDpODnl4VFdW2l+ntkp68GOOdWj+IZ8gM15/iP8qx++rNf3aSvZ0J9AqhlsCBkxhoEUsv+Hnl7Z84EDy+tLLAbXhiQUaJf/e8qsH0LBunS8adaPsjb4Z7mR+hx88NVnNEfLh7zX3ZT615PsmdeFKtbtTwSBIGEo3/bz/DsEkWTkbjRjuDFAalBR7DLx/Y2jONPjYvrxrCTy4eIJXRqcPon+J+Fuh1e22S8Z7FkVC9WrkRRrE2QFnuR+Od4WWBhvDggJZXLFGcLhnc3tONPq7EQ5QvQkbt7evSHik17TscO44HIWDyDt8wd0pVVRAth8D5JfPJtbnh0p4YFCePwAhm8rM/mz5rX/patmRgEJyOxr8CFtaPZGZ50d2zpQFmSCOln0M+iIIN+tl7zLeRiZXRf1rRWZbEkFWvY8Fn/g+HJnT4F8P43Twi8gRfJeBQtmJ+Fd27Jji4fBqciQ0UBXRmc450HKRufrg6OLx3Eji4ZyIl6y0Du5AeJ/4x+DXXmo6/7K3D2TnN4yVAW0rE5K5w/H9a0MYf9XPr884C/w5O7FAQI3+NlMgaBuXKzLq3rsXWTB8CBAF2xZlJ/1qlVHZYnVy6sdaMkKlusleDBZakKsC3AC6XPTP5SRQuz3j81EjdIOApgiGCAX2f1bNeQlShSEBUE+mUqPLdM8vX1/YIGKOCl0kG3vjxFWJVW/VjzATPZ/kVD4RCAodm7aAhr2n8mq9yyD8uauxD2CB1A19Zmc8B/4LnlvQpogJdLe2TJnp9VbNKVVe+3iJWIPMpswdeY9S29pmyDEwCGpvOkne/WA62NEpFHWI0+C/ia6SKuHewhmjz6rw6PLb8+4d2UluEFUzeCfw5Wtu7PrEbPWaxMZByzB199t8GlxvQFsXAEwJBMmLcq3bVBa6dMRJy4lsrWbsdsfphDoAHmwVUrdgqQOROuAtSFry0LK1G1KavRdSKrELGHZQ69ku6m9iFZ+K/fEjsXDgEYivUxC5h/iKNr5TKrGLGb1egSzYp/21hce9iDVHX0f9Nqtf4/eGplrwJq4WXz4PGWrx8rVLYm++63Eaxy+FaWI+yiQ5tYahSJimcn1k2DYwCG4Mja6axA1DmX1w2tvSphW1j1X4ezgqWri2sTe5QnR/0K38JDu6UqwDodL5wby/MKl2PV2kWyasHrWN7weJc3rtSoOeIou7RxIhwE0DUXNk5iVYcdU2QN5Y2I52t0DavWNpzlKVQGe5dbJ/0J4+GZ3SSLxfIvng9wGS+eMuQI/EbM1P9uQCwrHHlKkc0qNX6dsEucfgdHAfQ62bHt+L1uW09FI0+y6v2XixUGVH2DvU2ho3+LNR4Nf9x+FWAthZdPHjJnyytmHVOmfvHIY27boFJj2Kx1cBZAl0TM2OixdfWmwuCoWGFQoVFnljlrHux9MvX6576oCDyyR64ChCi8gI5D2cSUVVyjx0yeZXyQ2R1MRlJ6o5q3aBkcBtAV0xfGqmaNiRUGfM2XjTjAKwxmsDK1fmRWe1bsjU4d/dsGwBN7SAEBAX+iUYt4ETPO1C9ZrZnTmfruJiDsMtsUOw+OA+ij81/MQocz/t2+5lJUGFBVDyoMpBz9C9tMpuJ/gCf2oMxmm8DzAR7hhXw/U79I+dqs+m8jWZXwbSy7DJn67iZ/5Dl2YM1MOBCg7U5/q2ezwMjzmlt/VGFAVT5U7UNVP6gw+Kjk776Pj48FHlgdpYENje7w85eowqq1H8iqhmxgecLPaW7DSY1Sg0+zk+umwpEATXKMl/t9M/CsLtYiVf9UC1nHqv4UyfIWq4QgwFuoCc+rpiCAD18w0guYq2Ap9m2bEJ6pv4oViDiji00mNaoNP8bO89IpOBSgJeI3TGGVhp7Q7bosGHGaVR+wkn37Q3+WM39Jgzl/21h4XJWJyjDMXtbjen3paFBI5ea9WPWgZaxoxEndbiyp0WTsQXZ5E4IAoA2on0W9UYcMtUaLRhzne9MSVrlZTxaQq6Cev/wPZcqU6W/wuOq8CgjgD+mJLkrzeJkOletQ2c6HQ3SMyE/Re9AjAKiey5snslbj9ht6rdJeVTLyMKveex4r3/A35heQWyf3/taEr7+22uFpVd0kSKiv1dK8Mm9L88qqrDRPLfSYvB1BAFB1o5+OE3diraZScliOSg57TNd0yaHFYq8BD6uJkwDbaLW/TFZb5jeled0miaV5NOgDm0XGhE3fBGcDVEnQ1C1Yo46UHHadoJ2SQ29hEDyrRiQIwp/V1h/Ax+rPilasx6p3HMMqh21n2TRYmqcWhsxcD4cD0OVPJ1CZcuWwbWLZctEKdVRXcsjr/bcHBgb+EZ5VQ6IaTd4f4LYnS/MKlv6OfffLUPZt6CaWK/wCFruMjJiNlsFAHQyetR5rUkZyh5/ne+ZGvncOZgVKVvVoQMDr/a97efl/BY+qyXwAWwn+EF+5dWpe2whxIle+iHgsZoUZO3c1HBDwKCNmr8VaVLopWMRZ3oNg9Zsph4XLujPp7wX3IYXgSbWdD/CTYlPz8hZjVVoHvZmax+tisVjdn20cPW8VHBHwCKPmwPl7giIRp9h3/WPEiaXZA4soWe/fEh5U+/qEP8hJcrwQWXIUYJWadedT8xazYh6emgd+Z8ycNXBIAF/+BoUmmNIk04pNu7KAHPnlcv6j4Tp1cxVg+Qsf2bjLqVr8hh3FetZSvK5VCL6KBYecAIA7f6w5lUJ7dOnIQ6wG9SCo38HZHgRbadAcPKeurgIyZ+KlHJfSe/CCf453Y3JRi689aGOGgwJKEjVzA9aahnsQUL+V9DP+ree+FITP4TF1WRlgz5lycqBYi1+9Ba9HncwqhO9V/ZhckDH9p21GsyCgSJOf3lO2YY1pHOq3UjF8jzganfqwkA9I2emPusnCU+pY35St2aR6p7GvK4XvYFlDL2FR6JDOk3ayK5smwnEB2dr7/jphF9aWDskm9iDYTj0IXhcp+V1teEgDqN6AxUfw8uubNuP3sUsYIARkGOzTcux+rCmd0yh4/h54RoOo1rx5n9UIX3cPL76+oYlsZ/lYVjgy4AynN0xltUYewVrSObXD1t6EVzSY2rYd+fcqEbueYQHom/JDT7LDa2fAoQGHOLRmBis75BTWkM6pEr7taa2+fZHxb0Q16TrGp1TE4VdYCPqmcFQ827FyDhwbkMTWFXNZ/sjzWDs6p1xk3MsW3Yd+AU9oYDXtOapw4fCTr7Eg9E2O8Ets+dIlcHAgXRYvWcqyhyM5WO98E3H8dZNuwwLhASFT825jGtAACiwMndcE8yYh1L4Vjg6kVuY3dNY6JoSg2ZfeCQyPZy2DRlaD54PeqVmv6N4oCzRKmeAOVAiA9zL9f5mwG2vDCCeBvOyvSZ/RHeHxoI+vA4KmDPdH9z9DQNndJ9dPhQM0OMfWTmc1RhzFmjAAAbwJUNPe0ZHwdFCaahw0bY4dPf8Nkxy4IXY+HKFBWRuzAMl+BmoJ3LTflMnwcFCGahQ0ez0WjTHwC73Kxs5djfbBBrvvp2l+fjjtM8zY8EZ9Z8TAs0HSg4CQRfuxeIzDT9F72PmNyAvQO/EbJ4tdIvHOG4eGQfN2wqNBDqte6KJjWEDGoTRv/LJt5Vw4Sp2yOXYeKzH4DN51A9EgeOEBeDLIadUJXXoaC8k4UBLoiNnrcCWgI2gw1JCZ68XrHrzjBnL+IYuOwoNBLqt+SMx5LChj0XhMHDuxbhocqMY5um66OBMC77SxqB+89BQ8FySLaHhQndCVl7GwjEXeiPNs3qLlcKQaZfrCWJY74gLeZYNRNzT2HLwWJH8QEBKDIMCgo4VPrkPPAK1wnJ/ctBiHEb5Gdf60V8NjQYqIXjAsNOORL5JOA5bBwaqcmQtiWR589ePYH4KQGAjkpvnYA+KoWDhbdbF/zUzWaHQc3lEk/EGQ8kKJoHHJyluKDp61nl3GPAFV9PGPnLGRZQlFUx+U+kGQG4VmQcamLO8bsGr5QjhiDxGzbDErNfg03kUDd/hrMGDODngiyGNqHDRrLWYHGBtKONu7ehacspvYtXK2WKaJd8/Yvf3R3hdSSRAwbU5mHEEae6YA35C6Tt6BCYMKZ/fTKGcE3Jjqh8E+kKpEo4Szhl7CAsW8cRbB76TPbpgCpy0TpzdMZaHTN7NsYVhfWF8XMdIXUqea9YrunTsc40XBNZYr4iILm7GJnVmPQMBpx89tF8wdf45wOH5wjQWGx7MmfUZ3hKeBVKvm3cY0KBx+8jUWLCByhl8Uv15P4WpAMnSN0n/aZpYdjh+85ZuI469bBo2sBg8Dqf86oOeowqUiDr/CwgXv7i3DLrOOE3eynavmwMmnwfYVc9mvE3ajpA+8R7nIuJdNug0LhGeBNBQEDPSqErHrGRYw+BAaTrN4yVJxSh0m9U1k8/m8hVojj+DdAB9RJXzb0xbdh34BjwJpTm3bjvx7jfB197CQQWoUHXhWvOPeZ8ASQiqbDJq2hRWOise7AFKldtjam7X69v0TPAmkWdFgioYhSw7bsKBBOtQccZRNmLdK19UDlM0/du5qVm34MTxzkHaNPy/zbBQ8fw+8B6SfK4F+02cHoIwJZNRPIPQqq8+vCMbMWcMOr9X+zAGamzByzlpWd9RhsVcCnjFIj+xU5td3ygR4DEh3ohKW/BFnsdCB9DvQYcfFcsK1MQvEvvdqd/gX+XyE1bw9MlU+VBp6As8QSKZg+KnXzXuPawtPAelWLbsOL4UKAeBsJQEly4Vw5xrL++DHq+C6gK4sli9dzAbwsr2aI44wdMQEzlAmIu4VVU/BQ0C6V7PuIZ9Xj9j8AAsfyJFI2HjMQTGhbvrCWLGUTok8AmpotG3lXDZ9QSzrN3WrOHq3CBL4gBz5L2Hr7rboO+g/8AyQoZIDaZqgHfeiQIm7VN5Ehybl1Rl5mP0UvYf1mrJNrDgYOHMDGzF7LRs/dxWbtmAFm8qh/07/H/0z+jX0a9vxf6c2P20oOeg0WvACxQb6YJofZOzkQD5DIFf4BWwIAABDtfVt2mdCGDwAhLwA3uKyZNShJGwMAAC9Uyri0KvGPUeVw84PQSnyAr6PWH8HGwQAQK/UCl91o2v4uH9ix4egVNR4wOw1GCsMANAT2Xh9f5O+05dgh4egDES1sDT9ChsHAEDrFA8/+rpJj5FNsbNDkNQrgW6jrdXDNyVgAwEAaJUaEevu0WA07OgQhCsBAIAByEFH/kEzl2IHhyBXrwS6jWlQIhJVAgAA9VM6PO5Vi16jamDnhiCZRJ2yaoetuoapggAANSLwKX51w2Iu0Rh07NgQpERuQL/JozBQCACgrkE+J1836zM+Cjs0BCmsH7oPC6geuekhNh4AgKf5PmLT/R96DbVjZ4YgN4qSbHKGoY0wAMD95A4/zxoHTZuDnRiCPHUl0HN0mSrhu55hQwIAuIvvIrc+ad1zZFHswBDkYdFkwcb9Z63CaQAAQEnyhJ9DRz8IUqMoIq8Wse0JNioAgNxQ3lGTbsMCsdNCkIrVtO/MRYER57BpAQBchqqOmvadOgM7KwRpRFQpUDNs3V30DQAAOFvXT9P7qC05dlQI0uJpQO8JwTSMAxsaAEAq1Hm0ed8JPbCDQpDGRfO364csOR4QehmbGwAgTQLCLrGG/Rce/L7DoL9i54QgHal5rxFVqXwHGx0A4KPSvogtj6isGDslBOlY1E74m/BjuBYAALDikUeT0MYXggx2LdCg/6JD2fjYTmyCABiPnOEXWKPg+Xtw3A9BBhX1DqgRvuGunWf8YlMEQP/YQ66wmhFrb7XqNiofdkAIgkzNe49rWzFy53NskADolypRuxIb9RrZHDseBEEfqUmfydEoGwRAZ2V9UYeTmgZNGY4dDoKgdEV3go2C5mwsGHYagQAAGqZQ+MnXDYNmrq7Vt++fsLNBECRZzbqHfN4geOEBGgCCzRQA7ZA3Ip7X88/fTcm+2MkgCHL+WqDrGJ+6wcvPZEfFAACqz+xvELL0ePO+I77CzgVBkHyBQI/IXHVCl11AIACA+hw/Bemtuw/PjJ0KgiDF1LTLoOz1Q2LO50AgAIBnHX/YBVYndOnpH3oNtWNngiDIbaKJgw1ClsXT1wc2YwDcRy6+5uoHLz0Fxw9BkEdF40Ibhiw5THPDsTkDoBxUmdMoZNH+pj0HemHngSBINaKMYyofLIo5AwDI26+f9+agcr62bUf+HTsNBEGqFdUcNw6aNqd85P6X2LwBcJ6KEXteNA2aNq3WvHmfYWeBIEhTatp7Qqfq4ZsS/EIwawAAKWQOvcK+j9h0v1nvse2xg0AQpHm16j6seN3Q2HNoKgRA6gSGx7N6ocvONu05qjB2DAiCdKcWfQf9p/GAWbG4HgDg92P+Jn2nL0HXPgiCDKNmvUe3rh229ib6CQBj1u+vut6kx8im2AkgCDKsqKSJqgfKRR3AqQDQLTbxa3//i8YDZq9p0X3oF1j5EARBKU8Feo6pVyck9mL+cPQUADqp3Q8/9ZoaZrUMGlkNKxyCICgDUSlhkz6To6tHbnoYEHYJjgRoimz8WouqX5r3njQSo3ghCIJcuSLoOyOmSviuZ/aQK3AwQJX483ezSvi2p037zlyEI34IgiCZRRMJGwbN20mZ0/Zg9BYAnoUC0oqRu5436j93C83GwAqFIAhyRzDQbVhgk+B5mytG7nyORkPAvV/6u5417D9rA03GxEqEIAjyoOjri7Krv4vc+iQbygqBzFCparXwbY+b9J+5AhP4IAiCVCpqqNKs3+RRtcJX3SgSfhKDiYBTfBNx/HXtsFXXmvSdPBRDeCAIgjSoRr1GNq8fvCiucsSuZ6goAOll7tPRPo3bbd5tTAOsHAiCIB2JWhHTF13dsJhLpSIOvxKQSGjcBD7+7EtFHHpVJ3TZhaa9oyPxlQ9BEGQgte4+PHPTPlOn1g5ZcZWcAcoM9QslipaLjHtZJyTmcpOgyZOadRttxQqAIAiCRFHPgab9poypHxJznsq7MKdA2/32qUKEuvBRQ57mfUd8hTccgiAIkqRa8+Z9RjkENKugRviGu8UjjySh5FCdpXklIg8lfR+x/k6joNnrG/cY3pieHd5gCIIgSDZRHkHT3hM6UQ+C70PX3KZj5ayhSC50F9n5qUz5yAMvaZIkBWbNuo/rgFG6EARBkMdOCmjYC+UT1AtddKx6xOYHlGSIwMA1R095GTUiNj6oN2DxEX41M7l5l1GV8WUPQRAEaSIwaNZzdJkmfScNpnaxlIBWLWLbkxJRh5OQX/CmwQ4d3X8XsfUJVWbwZk6bmvUZH9Wy6/BScPQQBEGQbkVd5Zr3HteWTg5oxgGNQqYJiDTroHjk0aTA8HimxZkHVE2RNyKeFY86klQxcs9zmoxXJyz2QoMBc3ZQBn6THmPbIAsfgiAIgtI7ReDjZFt1G5VPDBT6TRnWMGja/CZB8zY3DFl4uH7I8vO1I9bcqhG58WHViG1PK/Gs9/KR+1+WiopLKh5x9HXhiJOv83FHHBhxjuUOP89yhl8QG95kDb0sJs5lDr0i/nc6bqd/Rr8mLw866N+hf5d+RunIg0kVova/pJ9Nvwf9XvR71g9efr7BgIWH6Kud/kz0ZyPHTjMaMAIXgtSv/w/9cE2eWtqsZQAAAABJRU5ErkJggg=="

    const val POINTS = "points"
    const val RECORD_PERSONAL = "personalRecord"
    const val TOTAL_TEAMS_SPAIN_FIRST_DIVISION = 19
    const val PATH_REFERENCE_TEAMS = "stadiums/"
    const val PATH_REFERENCE_SPAIN = "spain/"
    const val PATH_REFERENCE_FIRST_DIVISION = "first_division/"
    const val PATH_REFERENCE_APPS = "country/apps"
    const val COLLECTION_RANKING = "ranking-teams"

    const val TYPE_GAME = "TypeGame"
    const val TYPE_CHAMPIONSHIP = "TypeChampionship"
    enum class TypeGame { BY_NAME, BY_IMAGE, BY_CAPACITY, BY_BUILT }
    enum class TypeChampionship {
        SPAIN_FIRST_DIVISION, SPAIN_SECOND_DIVISION,
        ENGLAND_FIRST_DIVISION, ENGLAND_SECOND_DIVISION,
        ITALY_FIRST_DIVISION, ITALY_SECOND_DIVISION
    }
}